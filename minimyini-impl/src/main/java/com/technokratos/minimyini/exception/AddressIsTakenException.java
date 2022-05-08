package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class AddressIsTakenException extends MinimyiniException {
    public AddressIsTakenException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Address is taken");
    }
}
