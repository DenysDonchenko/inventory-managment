package com.example.inventorym.services;

import com.example.inventorym.dto.UserRegisterBO;
import com.example.inventorym.entity.User;
import com.example.inventorym.util.exception.UserWithEmailAlreadyExistException;

public interface UserService {

    void createNewUser(UserRegisterBO userDto) throws UserWithEmailAlreadyExistException;

    User findUserByEmail(String userEmail);

}
