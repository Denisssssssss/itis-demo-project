package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class HotelNotFoundException extends MinimyiniException {
    public HotelNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Hotel not found");
    }
}
