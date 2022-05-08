package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class ApartmentNotFoundException extends MinimyiniException {
    public ApartmentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Apartment not found");
    }
}
