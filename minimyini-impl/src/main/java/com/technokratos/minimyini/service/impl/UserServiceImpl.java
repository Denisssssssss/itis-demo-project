package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.UserDto;
import com.technokratos.minimyini.exception.UserNotFoundException;
import com.technokratos.minimyini.exception.UsernameTakenException;
import com.technokratos.minimyini.model.User;
import com.technokratos.minimyini.repository.UserRepository;
import com.technokratos.minimyini.service.UserService;
import com.technokratos.minimyini.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public User save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (userRepository.findByUsername(userDto.getUsername()).isEmpty()) {
            return userRepository.save(userMapper.toEntity(userDto));
        }
        throw new UsernameTakenException();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
