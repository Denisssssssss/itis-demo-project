package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception for cases then username is taken
 */
public class UsernameTakenException extends MinimyiniException {
    public UsernameTakenException() {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Username is already taken");
    }
}
