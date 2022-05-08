package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class ApartmentExistsException extends MinimyiniException {
    public ApartmentExistsException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Apartment already exists");
    }
}
