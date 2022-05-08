package com.technokratos.minimyini.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Some abstract exception
 */
@Getter
@AllArgsConstructor
public class MinimyiniException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;
}
