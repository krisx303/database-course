package com.group.travels.api.payments;


import com.group.travels.api.IllegalOperationException;
import com.group.travels.domain.booking.Booking;
import com.group.travels.domain.booking.BookingState;
import com.group.travels.domain.booking.BookingStorage;
import com.group.travels.domain.customer.CustomerStorage;
import com.group.travels.domain.discount.DiscountStorage;
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

    @Autowired
    private DiscountStorage discountStorage;

    @Operation(description = "Get customer payments by customer id")
    @GetMapping("/customer/{id}")
    ResponseEntity<List<PaymentResponse>> getAllByCustomerID(Long id) {
        var payments = paymentStorage.getPaymentsByCusomerId(id).stream().map(PaymentResponse::new).toList();
        return ResponseEntity.ok(payments);
    }

    @Operation(description = "Pay for bookings by customer id")
    @PutMapping("/customer/{id}/pay")
    ResponseEntity<List<Booking>> payForBookings(Long id, PaymentRequest paymentRequest) {
        if(paymentRequest.discountCode()!=null){
            if(discountStorage.findByCode(paymentRequest.discountCode())==null){
                throw new IllegalOperationException("Discount code is not valid");
            }
            if(discountStorage.findByCode(paymentRequest.discountCode()).getUsed()){
                throw new IllegalOperationException("Discount code is already used");
            }
        }

        for (Long bookingID: paymentRequest.bookingIDs()) {
            Booking booking = bookingStorage.findByID(bookingID);
            if (booking.getBookingState() == BookingState.PAID)
                throw new IllegalOperationException("Booking is already paid");

            if (booking.getBookingState() == BookingState.CANCELLED)
                throw new IllegalOperationException("Cannot make a payment, booking is cancelled!");

            if (booking.getCustomer().getCustomer_id() != id) {
                throw new IllegalOperationException("Cannot make a payment, booking is not yours!");
            }
        }

        List<Booking> bookings= new ArrayList<>();
        for (Long bookingID: paymentRequest.bookingIDs()) {
            Booking booking = bookingStorage.findByID(bookingID);
            Booking updated=bookingStorage.changeBookingState(booking, BookingState.PAID);
            bookings.add(updated);
            paymentStorage.create(booking,
                    paymentRequest.discountCode()==null?-1:discountStorage.findByCode(paymentRequest.discountCode()).calculateDiscount(booking.getTravel().getPrice()));
            logStorage.logChange(updated);
        }

        if (paymentRequest.discountCode()!=null){
            discountStorage.useDiscount(discountStorage.findByCode(paymentRequest.discountCode()).getId());
        }
        return ResponseEntity.ok(bookings);
    }
}
