package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class WrongCredentialsException extends MinimyiniException {
    public WrongCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "Wrong credentials");
    }
}
