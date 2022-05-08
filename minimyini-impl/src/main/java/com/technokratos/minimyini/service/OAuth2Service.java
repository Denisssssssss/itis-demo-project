package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.TokenDto;

public interface OAuth2Service {

    TokenDto signIn(String code);
}
