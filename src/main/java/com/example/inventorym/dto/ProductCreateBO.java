package com.example.inventorym.dto;

import com.example.inventorym.entity.enums.TypeProduct;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductCreateBO {

    private String name;
    private String description;
    private BigDecimal price;
    private String type;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ProductCreateBO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", email='" + email + '\'' +
                '}';
    }
}
