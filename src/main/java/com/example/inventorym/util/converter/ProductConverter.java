package com.example.inventorym.util.converter;

import com.example.inventorym.dto.DocumentRefusedBO;
import com.example.inventorym.dto.ProductBO;
import com.example.inventorym.dto.ProductCreateBO;
import com.example.inventorym.entity.Product;
import com.example.inventorym.entity.User;
import com.example.inventorym.entity.enums.TypeProduct;

import java.time.LocalDateTime;

public final class ProductConverter {

    private ProductConverter() {
    }

    public static Product toProduct(ProductCreateBO productBO, User user) {
        var product = new Product();
        product.setDescription(productBO.getDescription());
        product.setName(productBO.getName());
        product.setType(TypeProduct.valueOf(productBO.getType()));
        product.setPrice(productBO.getPrice());
        product.setCreatedAt(LocalDateTime.now());
        product.setUser(user);
        product.setCount(0);
        return product;
    }

    public static ProductBO toProductBO(Product product) {
        var productBO = new ProductBO();
        productBO.setName(product.getName());
        productBO.setProductId(product.getId());
        productBO.setPrice(product.getPrice());
        productBO.setDescription(product.getDescription());
        productBO.setType(product.getType());
        productBO.setCreatedAt(product.getCreatedAt());
        productBO.setCount(product.getCount());

        return productBO;
    }

}
