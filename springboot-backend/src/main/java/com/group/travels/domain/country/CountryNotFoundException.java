package com.group.travels.domain.country;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(Long id) {
        super("Country with id '%d' not found".formatted(id));
    }
}
