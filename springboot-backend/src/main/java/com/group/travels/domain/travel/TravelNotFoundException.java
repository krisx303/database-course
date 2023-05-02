package com.group.travels.domain.travel;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TravelNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TravelNotFoundException(Long id) {
        super("Travel with id '%d' not found".formatted(id));
    }
}
