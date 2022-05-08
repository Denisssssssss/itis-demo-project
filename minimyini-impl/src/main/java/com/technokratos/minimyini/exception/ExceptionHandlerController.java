package com.technokratos.minimyini.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception e) {
        return ResponseEntity.badRequest()
                .body(ExceptionDto.builder()
                        .message(e.getMessage())
                        .cause(e.getClass().getSimpleName())
                        .build());
    }

    @ExceptionHandler(MinimyiniException.class)
    public ResponseEntity<ExceptionDto> handleCustomException(MinimyiniException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ExceptionDto.builder()
                        .message(e.getMessage())
                        .cause(e.getClass().getSimpleName())
                        .build());
    }
}
