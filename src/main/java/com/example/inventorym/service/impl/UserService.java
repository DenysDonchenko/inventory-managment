package com.example.inventorym.service.impl;

import com.example.inventorym.dto.UserRegisterBO;
import com.example.inventorym.entity.User;
import com.example.inventorym.repository.UserRepository;
import com.example.inventorym.util.exception.UserWithEmailAlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.example.inventorym.util.converter.UserConverter.toUser;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createNewUser(UserRegisterBO userDto) throws UserWithEmailAlreadyExistException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserWithEmailAlreadyExistException(userDto.getEmail());
        }
        var pass =  passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(pass);
        userRepository.save(toUser(userDto));
    }

    @Transactional
    public User findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).get();
    }
}
