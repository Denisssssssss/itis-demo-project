package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class BookingNotFoundException extends MinimyiniException {
    public BookingNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Booking not found");
    }
}
