package com.technokratos.minimyini.service;

import com.technokratos.minimyini.dto.UserDto;
import com.technokratos.minimyini.model.User;

import java.util.Optional;

public interface UserService {

    User save(UserDto userDto);

    Optional<User> findByUsername(String username);

    User findById(Long userId);

    void updateAuth(String username);
}
