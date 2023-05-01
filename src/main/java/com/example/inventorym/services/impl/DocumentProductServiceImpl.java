package com.example.inventorym.services.impl;

import com.example.inventorym.entity.DocumentProduct;
import com.example.inventorym.repository.DocumentProductRepository;
import com.example.inventorym.services.DocumentProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentProductServiceImpl implements DocumentProductService {

    private final DocumentProductRepository documentProductRepository;

    public DocumentProductServiceImpl(DocumentProductRepository documentProductRepository) {
        this.documentProductRepository = documentProductRepository;
    }

    @Override
    public List<DocumentProduct> findByDocumentId(UUID documentId) {
        return documentProductRepository.findDocumentProductByDocumentId(documentId);
    }

    @Override
    public List<DocumentProduct> findById(UUID id) {
        return documentProductRepository.findDocumentProductById(id);
    }

    @Override
    public void save(DocumentProduct documentProduct) {
        documentProductRepository.save(documentProduct);
    }

}
