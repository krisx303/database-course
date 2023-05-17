package com.group.travels.domain.payments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PaymentNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PaymentNotFoundException(Long id) {
        super("Payment with id '%d' not found".formatted(id));
    }
}
