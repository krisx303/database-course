package com.group.travels.api.payments;


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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //get all payments by customer id
    @Operation(description = "Get customer payments by customer id")
    @GetMapping("/customer/{id}")
    ResponseEntity<List<PaymentResponse>> getAllByCustomerID(Long id) {
        var payments = paymentStorage.getPaymentsByCusomerId(id).stream().map(PaymentResponse::new).toList();
        return ResponseEntity.ok(payments);
    }

}
