package com.group.travels.api.booking;

import com.group.travels.api.IllegalOperationException;
import com.group.travels.domain.booking.Booking;
import com.group.travels.domain.booking.BookingState;
import com.group.travels.domain.booking.BookingStorage;
import com.group.travels.domain.customer.Customer;
import com.group.travels.domain.customer.CustomerStorage;
import com.group.travels.domain.log.LogStorage;
import com.group.travels.domain.payments.Payment;
import com.group.travels.domain.payments.PaymentStorage;
import com.group.travels.domain.travel.Travel;
import com.group.travels.domain.travel.TravelStorage;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private BookingStorage bookingStorage;

    @Autowired
    private TravelStorage travelStorage;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private LogStorage logStorage;

    @Autowired
    private PaymentStorage paymentStorage;


    @Operation(description = "Get all bookings from database")
    @GetMapping
    ResponseEntity<List<Booking>> getAll() {
        var bookings = bookingStorage.findAll();
        return ResponseEntity.ok(bookings);
    }
    @Operation(description = "Get Booking by given ID")
    @GetMapping("/{id}")
    ResponseEntity<Booking> getByID(@PathVariable Long id) {
        Booking booking = bookingStorage.findByID(id);
        return ResponseEntity.ok(booking);
    }

    @Operation(description = "Creates new Booking")
    @PostMapping
    ResponseEntity<Booking> create(@RequestBody CreateBookingRequest details) {
        Customer customer = customerStorage.findByID(details.customerID());
        Travel travel = travelStorage.findByID(details.travelID());

        if (travel.getNumberOfFreePlaces() > 0) {
            travelStorage.reservePlaceOnTrip(travel);
            Booking saved = bookingStorage.create(customer, travel);
            logStorage.logCreate(saved);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } else {
            throw new IllegalOperationException("There is no free places on this trip!");
        }
    }

    @Operation(description = "Updates state of Booking with given ID to CANCELLED")
    @PutMapping("/{id}/cancel")
    ResponseEntity<Booking> cancelBooking(@PathVariable Long id) {
        Booking booking = bookingStorage.findByID(id);

        if(booking.getBookingState() == BookingState.CANCELLED)
            throw new IllegalOperationException("Booking is already cancelled");

        travelStorage.undoReservationOnTrip(booking.getTravel());
        Booking updated = bookingStorage.changeBookingState(booking, BookingState.CANCELLED);

        logStorage.logChange(updated);

        return ResponseEntity.ok(updated);
    }
    /*
    @Operation(description = "Updates state of Booking with given ID to PAID, and add payment to history")
    @PutMapping("/{id}/paid")
    ResponseEntity<Booking> payBooking(@PathVariable Long id) {
        Booking booking = bookingStorage.findByID(id);

        if(booking.getBookingState() == BookingState.PAID)
            throw new IllegalOperationException("Booking is already paid");

        if(booking.getBookingState() == BookingState.CANCELLED)
            throw new IllegalOperationException("Cannot make a payment, booking is cancelled!");

        Booking updated = bookingStorage.changeBookingState(booking, BookingState.PAID);

        paymentStorage.create(booking.getCustomer(), booking.getTravel());

        logStorage.logChange(updated);

        return ResponseEntity.ok(updated);
    }
    */


    @Operation(description = "Delete Booking by ID")
    @DeleteMapping("/{id}")
    ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id) {
        bookingStorage.delete(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Get all Booking assigned to Travel with given ID")
    @GetMapping("/travel/{id}")
    ResponseEntity<List<Booking>> getAllBookingByTravelID(@PathVariable Long id){
        Travel travel = travelStorage.findByID(id);

        return ResponseEntity.ok(travel.getBookings());
    }

    @Operation(description = "Get all Booking assigned to Customer with given ID")
    @GetMapping("/customer/{id}")
    ResponseEntity<List<Booking>> getAllBookingByCustomerID(@PathVariable Long id){
        Customer customer = customerStorage.findByID(id);

        return ResponseEntity.ok(customer.getBookings());
    }

}
