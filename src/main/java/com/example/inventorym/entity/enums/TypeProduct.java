package com.example.inventorym.entity.enums;

import java.util.List;

public enum TypeProduct {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    FOOD("Food"),
    BEAUTY("Beauty"),
    HOME("Home"),
    SPORTS("Sports"),
    OTHER("Other");

    private final String value;

    TypeProduct(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<TypeProduct> allValues() {
        return List.of(ELECTRONICS, FOOD, SPORTS, HOME, CLOTHING);
    }
}
