package com.group.travels.domain.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(Long id) {
        super("Customer with id '%d' not found".formatted(id));
    }
}
