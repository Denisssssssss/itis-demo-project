package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class ApartmentModificationException extends MinimyiniException {

    public ApartmentModificationException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
