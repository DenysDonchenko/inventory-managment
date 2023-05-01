package com.example.inventorym.services.impl;

import com.example.inventorym.entity.Document;
import com.example.inventorym.repository.DocumentRepository;
import com.example.inventorym.services.DocumentService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }


    @Override
    public Document findById(UUID documentId) {
        return documentRepository.findById(documentId).get();
    }

    @Override
    public boolean isExistsDocument(String docNumber) {
        return documentRepository.existsByDocumentNumber(docNumber);
    }

    @Override
    public List<Document> findByDocNumber(String docNumber, UUID userId, Sort sortOrder) {
        return documentRepository.searchDocumentByNumber(docNumber, userId, sortOrder);

    }

    @Override
    public List<Document> findAllByUser(UUID userID, Sort sort) {
        return documentRepository.findAllByUser(userID, sort);
    }

    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
    }
}
