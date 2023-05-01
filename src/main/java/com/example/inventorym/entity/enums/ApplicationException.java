package com.example.inventorym.entity.enums;

public enum ApplicationException {

    USER_WITH_EMAIL_NOT_FOUND("error_user_with_email_not_found", "User with email - '%s' not found."),
    USER_WITH_EMAIL_ALREADY_EXIST("error_user_email_already_exist", "User with given email - '%s' already exist.");

    private String code;
    private String text;

    ApplicationException(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
