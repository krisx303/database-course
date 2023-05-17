package com.group.travels.api.payments;


import com.group.travels.api.IllegalOperationException;
import com.group.travels.domain.booking.Booking;
import com.group.travels.domain.booking.BookingState;
import com.group.travels.domain.booking.BookingStorage;
import com.group.travels.domain.customer.CustomerStorage;
import com.group.travels.domain.log.LogStorage;
import com.group.travels.domain.payments.Payment;
import com.group.travels.domain.payments.PaymentStorage;
import com.group.travels.domain.travel.TravelStorage;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private LogStorage logStorage;

    @Autowired
    private TravelStorage travelStorage;

    @Autowired
    private CustomerStorage customerStorage;

    @Autowired
    private BookingStorage bookingStorage;

    @Autowired
    private PaymentStorage paymentStorage;

    @Operation(description = "Get customer payments by customer id")
    @GetMapping("/customer/{id}")
    ResponseEntity<List<PaymentResponse>> getAllByCustomerID(Long id) {
        var payments = paymentStorage.getPaymentsByCusomerId(id).stream().map(PaymentResponse::new).toList();
        return ResponseEntity.ok(payments);
    }

    @Operation(description = "Pay for bookings by customer id")
    @PutMapping("/customer/{id}/pay")
    ResponseEntity<List<Booking>> payForBookings(Long id, PaymentRequest paymentRequest) {
        List<Booking> bookings= new ArrayList<>();
        for (Long bookingID: paymentRequest.bookingIDs()) {
            Booking booking = bookingStorage.findByID(bookingID);
            if(booking.getBookingState() == BookingState.PAID)
                throw new IllegalOperationException("Booking is already paid");

            if(booking.getBookingState() == BookingState.CANCELLED)
                throw new IllegalOperationException("Cannot make a payment, booking is cancelled!");

            if(booking.getCustomer().getCustomer_id()!=id){
                throw new IllegalOperationException("Cannot make a payment, booking is not yours!");
            }
            Booking updated=bookingStorage.changeBookingState(booking, BookingState.PAID);
            bookings.add(updated);
            paymentStorage.create(booking.getCustomer(), booking.getTravel());
            logStorage.logChange(updated);
        }


        return ResponseEntity.ok(bookings);
    }
}
