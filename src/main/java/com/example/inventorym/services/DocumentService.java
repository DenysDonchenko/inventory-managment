package com.example.inventorym.services;

import com.example.inventorym.entity.Document;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface DocumentService {

    Document findById(UUID documentId);

    boolean isExistsDocument(String docNumber);

    List<Document> findByDocNumber(String docNumber, UUID userId, Sort sortOrder);

    List<Document> findAllByUser(UUID userID, Sort sort);

    Document save(Document document);


}
