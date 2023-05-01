package com.example.inventorym.services;

import com.example.inventorym.entity.Product;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> findAllByUser(UUID userId, Sort sortProduct);

    void save(Product product);

    Product findByName(String nameProduct);

    Product findById(UUID productId);

    List<Product> findByNameAndUser(String nameProduct, UUID userId, Sort sortOrder);


}
