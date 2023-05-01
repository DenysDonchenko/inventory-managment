package com.example.inventorym.util.exception;


import org.springframework.http.HttpStatus;

import static com.example.inventorym.entity.enums.ApplicationException.USER_WITH_EMAIL_ALREADY_EXIST;
import static java.lang.String.format;

public class UserWithEmailAlreadyExistException extends ServiceException {

    public UserWithEmailAlreadyExistException(String email) {
        super(HttpStatus.CONFLICT, USER_WITH_EMAIL_ALREADY_EXIST.getCode(), format(USER_WITH_EMAIL_ALREADY_EXIST.getText(), email));
    }
}