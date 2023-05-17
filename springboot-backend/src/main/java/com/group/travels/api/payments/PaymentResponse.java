package com.group.travels.api.payments;

import com.group.travels.domain.payments.Payment;

import java.time.LocalDateTime;

public record PaymentResponse(Long id,
                               LocalDateTime paymentDate,
                               Integer price,
                               String travelName) {
    public PaymentResponse(Payment payment){
        this(payment.getId(),
                payment.getPaymentDate(),
                payment.getPrice(),
                payment.getTravel().getTravelName());
    }
}