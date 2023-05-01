package com.example.inventorym.service.impl;

import com.example.inventorym.dto.*;
import com.example.inventorym.entity.Document;
import com.example.inventorym.entity.DocumentProduct;
import com.example.inventorym.entity.enums.Action;
import com.example.inventorym.repository.DocumentProductRepository;
import com.example.inventorym.repository.DocumentRepository;
import com.example.inventorym.util.converter.ProductConverter;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.inventorym.util.DocumentUtil.generateDocNumber;
import static com.example.inventorym.util.converter.DocumentConverter.*;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentProductRepository documentProductRepository;
    private final ProductService productService;
    private final UserService userService;

    public DocumentService(DocumentRepository documentRepository, DocumentProductRepository documentProductRepository, ProductService productService, UserService userService) {
        this.documentRepository = documentRepository;
        this.documentProductRepository = documentProductRepository;
        this.productService = productService;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<DocumentBO> findAllByUser(String userEmail, Sort sortOrder) {
        var user = userService.findUserByEmail(userEmail);
        return toDocumentsBO(documentRepository.findAllByUser(user.getId(), sortOrder));
    }

    @Transactional(readOnly = true)
    public DocumentExportBO findDocByIdForExport(UUID id) {
        var doc = documentRepository.findById(id).get();
        var documentProducts = documentProductRepository.findDocumentProductByDocumentId(id);
        return toDocumentExportBO(documentProducts, doc);
    }

    @Transactional(readOnly = true)
    public List<ProductBO> findProductByDocId(UUID id) {
        return documentProductRepository
                .findDocumentProductById(id).stream().map(DocumentProduct::getProduct)
                .toList()
                .stream().map(ProductConverter::toProductBO).collect(Collectors.toList());
    }

    @Transactional
    public void createReception(DocumentReceptionBO documentReceptionBO, String name) {
        var product = productService.findProductByName(documentReceptionBO.getNameProduct());
        var user = userService.findUserByEmail(name);


        var document = new Document();
        product.setCount(product.getCount() + documentReceptionBO.getCount());
        document.setCreatedAt(LocalDateTime.now());
        document.setTotalCount(BigDecimal.valueOf(documentReceptionBO.getCount()));
        document.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(documentReceptionBO.getCount())));
        document.setActions(Set.of(Action.RECEPTION));
        document.setDocumentNumber(findDocNumber());
        document.setUser(user);

        var docBO = documentRepository.save(document);
        var docProduct = new DocumentProduct();
        docProduct.setDocument(docBO);
        docProduct.setTotal_cost(product.getPrice().multiply(BigDecimal.valueOf(documentReceptionBO.getCount())));
        docProduct.setProduct(product);
        documentProductRepository.save(docProduct);

    }

    @Transactional
    public void createRefused(DocumentRefusedBO documentRefusedBO, String name) {
        var product = productService.findProductByName(documentRefusedBO.getNameProduct());
        var user = userService.findUserByEmail(name);

        var document = new Document();
        product.setCount(product.getCount() + 1);
        document.setCreatedAt(LocalDateTime.now());
        document.setTotalCount(BigDecimal.valueOf(1));
        document.setTotalPrice(product.getPrice());
        document.setActions(Set.of(Action.RETURN));
        document.setDocumentNumber(findDocNumber());
        document.setUser(user);

        var docBO = documentRepository.save(document);
        var docProduct = new DocumentProduct();
        docProduct.setDocument(docBO);
        docProduct.setTotal_cost(product.getPrice());
        docProduct.setProduct(product);
        documentProductRepository.save(docProduct);

    }

    private String findDocNumber() {
        var docNumber = generateDocNumber();
        if (documentRepository.existsByDocumentNumber(docNumber)) {
            findDocNumber();
        }
        return docNumber;
    }

    @Transactional(readOnly = true)
    public List<DocumentBO> searchDocument(String name, Sort sortOrder, DocumentSearchBO documentSearchBO) {
        var user = userService.findUserByEmail(name);
        System.out.println(documentSearchBO.getName());
        return toDocumentsBO(documentRepository.searchDocumentByNumber(documentSearchBO.getName(), user.getId(), sortOrder));

    }
}
