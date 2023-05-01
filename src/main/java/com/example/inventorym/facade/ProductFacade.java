package com.example.inventorym.facade;

import com.example.inventorym.dto.ProductBO;
import com.example.inventorym.dto.ProductCreateBO;
import com.example.inventorym.dto.ProductSearchBO;
import com.example.inventorym.entity.Product;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ProductFacade {

    List<ProductBO> findAllByUser(String userEmail, Sort sortOrder);

    void createProduct(ProductCreateBO productBO);

    Product findProductByName(String nameProduct);

    ProductBO findById(UUID id);

    void updateProduct(ProductBO productBO);

    List<ProductBO> searchProduct(ProductSearchBO productBO, String userEmail, Sort sortOrder);
}
