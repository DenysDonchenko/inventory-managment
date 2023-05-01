package com.example.inventorym.service.impl;

import com.example.inventorym.dto.ProductBO;
import com.example.inventorym.dto.ProductCreateBO;
import com.example.inventorym.dto.ProductSearchBO;
import com.example.inventorym.entity.Product;
import com.example.inventorym.repository.ProductRepository;
import com.example.inventorym.util.converter.ProductConverter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.inventorym.util.converter.ProductConverter.toProduct;
import static com.example.inventorym.util.converter.ProductConverter.toProductBO;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<ProductBO> findAllByUser(String userEmail, Sort sortOrder) {
        var user = userService.findUserByEmail(userEmail);
        return productRepository.findAllByUserId(user.getId(), sortOrder).stream()
                .map(ProductConverter::toProductBO).collect(Collectors.toList());
    }

    @Transactional
    public void createProduct(ProductCreateBO productBO) {
        var user = userService.findUserByEmail(productBO.getEmail());
        productRepository.save(toProduct(productBO, user));
    }


    @Transactional(readOnly = true)
    public Product findProductByName(String nameProduct) {
        return productRepository.findByName(nameProduct).get();
    }

    @Transactional(readOnly = true)
    public ProductBO findById(UUID id) {
        return toProductBO(productRepository.findById(id).get());
    }

    @Transactional
    public void updateProduct(ProductBO productBO) {
    var product  = productRepository.findById(productBO.getProductId()).get();
    product.setId(productBO.getProductId());
    product.setPrice(productBO.getPrice());
    product.setType(productBO.getType());
    product.setName(productBO.getName());
    product.setDescription(product.getDescription());
    productRepository.save(product);

    }

    @Transactional(readOnly = true)
    public List<ProductBO> searchProduct(ProductSearchBO productBO, String userEmail, Sort sortOrder) {
        var user = userService.findUserByEmail(userEmail);
        return productRepository.searchProductsByName(productBO.getName(), user.getId(), sortOrder).stream()
                .map(ProductConverter::toProductBO).collect(Collectors.toList());
    }
}
