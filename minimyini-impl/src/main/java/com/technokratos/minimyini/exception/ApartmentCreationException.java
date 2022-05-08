package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class ApartmentCreationException extends MinimyiniException {
    public ApartmentCreationException(String message) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }
}
