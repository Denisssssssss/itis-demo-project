package com.technokratos.minimyini.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ExceptionDto {
    private final String message;
    private final String cause;
}
