package com.example.inventorym.facade;

import com.example.inventorym.dto.*;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface DocumentFacade {

    List<DocumentBO> findAllByUser(String userEmail, Sort sortOrder);

    DocumentExportBO findDocByIdForExport(UUID documentId);

    List<ProductBO> findProductByDocId(UUID documentId);

    void createReception(DocumentReceptionBO documentReceptionBO, String userEmail);

    void createRefused(DocumentRefusedBO documentRefusedBO, String userEmail);

    List<DocumentBO> searchDocument(String name, Sort sortOrder, DocumentSearchBO documentSearchBO);


}
