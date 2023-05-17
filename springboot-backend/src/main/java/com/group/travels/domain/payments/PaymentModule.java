package com.group.travels.domain.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentModule {
    @Bean
    PaymentStorage paymentStorage(PaymentRepository paymentRepository) {
        return new PaymentStorage(paymentRepository);
    }

}