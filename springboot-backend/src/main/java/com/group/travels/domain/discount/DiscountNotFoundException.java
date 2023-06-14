package com.group.travels.domain.discount;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DiscountNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DiscountNotFoundException(Long id) {
        super("Discount with id '%d' not found".formatted(id));
    }

    public DiscountNotFoundException(String code){
        super("Discount with code '%s' not found!".formatted(code));
    }
}
