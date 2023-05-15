package com.group.travels.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class IllegalOperationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public IllegalOperationException(String message) {
        super("Illegal operation on data: '%s'".formatted(message));
    }
}

