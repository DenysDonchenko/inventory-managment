package com.example.inventorym.services.impl;

import com.example.inventorym.dto.ProductBO;
import com.example.inventorym.entity.Product;
import com.example.inventorym.repository.ProductRepository;
import com.example.inventorym.services.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllByUser(UUID userId, Sort sortProduct) {
        return productRepository.findAllByUserId(userId, sortProduct);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findByName(String nameProduct) {
        return productRepository.findByName(nameProduct).get();
    }

    @Override
    public Product findById(UUID productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> findByNameAndUser(String nameProduct, UUID userId, Sort sortOrder) {
        return productRepository.searchProductsByName(nameProduct, userId, sortOrder);
    }


}
