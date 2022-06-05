package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.TokenDto;
import com.technokratos.minimyini.dto.UserDto;
import com.technokratos.minimyini.exception.UserNotFoundException;
import com.technokratos.minimyini.exception.WrongCredentialsException;
import com.technokratos.minimyini.model.User;
import com.technokratos.minimyini.security.TokenProvider;
import com.technokratos.minimyini.service.AuthenticationService;
import com.technokratos.minimyini.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenDto signUp(UserDto userDto) {
        String token = tokenProvider.generate(
                userService.save(userDto).getId());
        return new TokenDto(token);
    }

    @Override
    public TokenDto signIn(UserDto userDto) {
        User user = userService.findByUsername(userDto.getUsername()).orElseThrow(UserNotFoundException::new);
        if (passwordEncoder.matches(userDto.getPassword(), user.getHashPassword())) {
            return new TokenDto(tokenProvider.generate(user.getId()));
        }
        throw new WrongCredentialsException();
    }

    @Override
    public TokenDto googleSignIn(String email, String firstName, String lastName) {
        User user = userService.findByUsername(email)
                .orElseGet(() -> userService.save(new UserDto(email, UUID.randomUUID().toString(), firstName, lastName, null)));
        return new TokenDto(tokenProvider.generate(user.getId()));
    }
}
