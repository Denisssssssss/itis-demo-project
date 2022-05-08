package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class BookedApartmentException extends MinimyiniException {
    public BookedApartmentException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Apartment is booked");
    }
}
