package com.technokratos.minimyini.controller;

import com.technokratos.minimyini.dto.TokenDto;
import com.technokratos.minimyini.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    @GetMapping(value = "/login/oauth2/code/google")
    public TokenDto login(@RequestParam("code") String code) {
        return oAuth2Service.signIn(code);
    }
}
