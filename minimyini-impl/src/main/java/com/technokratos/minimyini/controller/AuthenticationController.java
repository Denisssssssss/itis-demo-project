package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.TokenDto;
import com.technokratos.minimyini.dto.UserDto;
import com.technokratos.minimyini.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication controller (/signIn, /signUp)
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/signUp", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDto signUp(@RequestBody UserDto userDto) {
        return authenticationService.signUp(userDto);
    }

    @PostMapping(value = "/signIn", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public TokenDto signIn(@RequestBody UserDto userDto) {
        return authenticationService.signIn(userDto);
    }
}
