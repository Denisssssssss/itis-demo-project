package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception for cases then
 */
public class UserNotFoundException extends MinimyiniException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "User not found");
    }
}
