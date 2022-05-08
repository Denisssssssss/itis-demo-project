package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class CodeExpiredException extends MinimyiniException {
    public CodeExpiredException() {
        super(HttpStatus.FORBIDDEN, "Code expired");
    }
}
