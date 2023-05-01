package com.example.inventorym.services.impl;

import com.example.inventorym.dto.UserRegisterBO;
import com.example.inventorym.entity.User;
import com.example.inventorym.repository.UserRepository;
import com.example.inventorym.services.UserService;
import com.example.inventorym.util.exception.UserWithEmailAlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.inventorym.util.converter.UserConverter.toUser;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createNewUser(UserRegisterBO userDto) throws UserWithEmailAlreadyExistException {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserWithEmailAlreadyExistException(userDto.getEmail());
        }
        var pass = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(pass);
        userRepository.save(toUser(userDto));
    }

    @Override
    public User findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).get();
    }
}
