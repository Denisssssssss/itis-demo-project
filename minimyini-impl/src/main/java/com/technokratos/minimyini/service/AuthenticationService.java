package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.TokenDto;
import com.technokratos.minimyini.dto.UserDto;

public interface AuthenticationService {

    TokenDto signUp(UserDto userDto);

    TokenDto signIn(UserDto userDto);

    TokenDto googleSignIn(String email, String firstName, String lastName);
}
