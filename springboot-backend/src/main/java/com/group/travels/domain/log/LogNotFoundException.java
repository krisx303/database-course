package com.group.travels.domain.log;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LogNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public LogNotFoundException(Long id) {
        super("Log with id '%d' not found".formatted(id));
    }
}
