package com.technokratos.minimyini.exception;

import org.springframework.http.HttpStatus;

public class FacilityNotFoundException extends MinimyiniException{
    public FacilityNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Facility not found");
    }
}
