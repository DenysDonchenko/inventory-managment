package com.example.inventorym.facade.impl;

import com.example.inventorym.dto.ProductBO;
import com.example.inventorym.dto.ProductCreateBO;
import com.example.inventorym.dto.ProductSearchBO;
import com.example.inventorym.entity.Product;
import com.example.inventorym.facade.ProductFacade;
import com.example.inventorym.services.ProductService;
import com.example.inventorym.services.UserService;
import com.example.inventorym.util.converter.ProductConverter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.inventorym.util.converter.ProductConverter.toProduct;
import static com.example.inventorym.util.converter.ProductConverter.toProductBO;

@Component
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;
    private final UserService userService;

    public ProductFacadeImpl(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<ProductBO> findAllByUser(String userEmail, Sort sortOrder) {
        var user = userService.findUserByEmail(userEmail);
        return productService.findAllByUser(user.getId(), sortOrder).stream()
                .map(ProductConverter::toProductBO).collect(Collectors.toList());
    }

    @Transactional
    public void createProduct(ProductCreateBO productBO) {
        var user = userService.findUserByEmail(productBO.getEmail());
        productService.save(toProduct(productBO, user));
    }

    @Transactional(readOnly = true)
    public Product findProductByName(String nameProduct) {
        return productService.findByName(nameProduct);
    }

    @Transactional(readOnly = true)
    public ProductBO findById(UUID id) {
        return toProductBO(productService.findById(id));
    }

    @Transactional
    public void updateProduct(ProductBO productBO) {
        var product = productService.findById(productBO.getProductId());
        product.setId(productBO.getProductId());
        product.setPrice(productBO.getPrice());
        product.setType(productBO.getType());
        product.setName(productBO.getName());
        product.setDescription(product.getDescription());
        productService.save(product);
    }

    @Transactional(readOnly = true)
    public List<ProductBO> searchProduct(ProductSearchBO productBO, String userEmail, Sort sortOrder) {
        var user = userService.findUserByEmail(userEmail);
        return productService.findByNameAndUser(productBO.getName(), user.getId(), sortOrder).stream()
                .map(ProductConverter::toProductBO).collect(Collectors.toList());
    }
}
