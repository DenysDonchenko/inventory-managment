package com.example.inventorym.services;

import com.example.inventorym.entity.DocumentProduct;

import java.util.List;
import java.util.UUID;

public interface DocumentProductService {

    List<DocumentProduct> findByDocumentId(UUID documentId);

    List<DocumentProduct> findById(UUID id);

    void save(DocumentProduct documentProduct);

}
