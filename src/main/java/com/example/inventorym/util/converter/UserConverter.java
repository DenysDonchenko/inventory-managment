package com.example.inventorym.util.converter;

import com.example.inventorym.dto.UserRegisterBO;
import com.example.inventorym.entity.User;
import com.example.inventorym.entity.enums.Role;

import java.util.Set;

public final class UserConverter {

    private UserConverter() {
    }

    public static User toUser(UserRegisterBO userDto) {
        var user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRoles(Set.of(Role.USER));
        return user;
    }

}
