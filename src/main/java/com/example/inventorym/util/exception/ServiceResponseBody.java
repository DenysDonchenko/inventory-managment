package com.example.inventorym.util.exception;

import java.util.HashMap;
import java.util.Map;

public class ServiceResponseBody {

    private String message;
    private String code;
    private Map<String, String> data = new HashMap<>();

    public ServiceResponseBody() {
    }

    public ServiceResponseBody(String message) {
        this.message = message;
    }

    public ServiceResponseBody(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public ServiceResponseBody(String message, String code, Map<String, String> data) {
        this.message = message;
        this.code = code;
        this.data.putAll(data);
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public Map<String, String> getData() {
        return data;
    }
}
