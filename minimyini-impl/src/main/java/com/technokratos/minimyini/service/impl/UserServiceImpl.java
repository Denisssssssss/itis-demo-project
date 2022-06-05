package com.technokratos.minimyini.service.impl;

import com.technokratos.minimyini.dto.UserDto;
import com.technokratos.minimyini.exception.UserNotFoundException;
import com.technokratos.minimyini.exception.UsernameTakenException;
import com.technokratos.minimyini.model.User;
import com.technokratos.minimyini.repository.UserRepository;
import com.technokratos.minimyini.service.UserService;
import com.technokratos.minimyini.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;
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
            User user = userMapper.toEntity(userDto);
            user.setLastAuth(LocalDate.now());
            return userRepository.save(user);
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

    @Override
    public void updateAuth(String username) {
        User user =  userRepository.findByUsername(username).orElseThrow(EntityExistsException::new);
        user.setLastAuth(LocalDate.now());
        userRepository.save(user);
    }
}
