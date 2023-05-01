package com.example.inventorym.util.converter;

import com.example.inventorym.dto.DocumentBO;
import com.example.inventorym.dto.DocumentExportBO;
import com.example.inventorym.dto.DocumentRefusedBO;
import com.example.inventorym.dto.ProductExportBO;
import com.example.inventorym.entity.Document;
import com.example.inventorym.entity.DocumentProduct;
import com.example.inventorym.entity.enums.Action;

import java.util.List;
import java.util.stream.Collectors;

public final class DocumentConverter {

    private DocumentConverter() {
    }

    public static List<DocumentBO> toDocumentsBO(List<Document> documents) {
        return documents.stream().map(DocumentConverter::toDocumentBO).collect(Collectors.toList());
    }

    public static DocumentExportBO toDocumentExportBO(List<DocumentProduct> documentProducts, Document document) {
        var doc = new DocumentExportBO();
        doc.setTotalPrice(document.getTotalPrice());
        doc.setDocNumber(document.getDocumentNumber());
        doc.setCreatedAt(document.getCreatedAt());
        doc.setTypeDocument(document.getActions());
        doc.setTotalCount(document.getTotalCount());
        doc.setProductExportBOS(documentProducts.stream().map(DocumentConverter::toProductExportBO).collect(Collectors.toList()));
        return doc;
    }

    private static ProductExportBO toProductExportBO(DocumentProduct documentProduct) {
        return new ProductExportBO(documentProduct.getProduct().getName(),documentProduct.getProduct().getPrice());
    }


    private static DocumentBO toDocumentBO(Document document) {
        var documentBO = new DocumentBO();
        documentBO.setDocumentId(document.getId());
        documentBO.setTypeDocument(document.getActions());
        documentBO.setCreatedAt(document.getCreatedAt());
        documentBO.setTotalCount(document.getTotalCount());
        documentBO.setTotalPrice(document.getTotalPrice());
        documentBO.setDocNumber(document.getDocumentNumber());
        documentBO.setSale(document.getActions().contains(Action.SALE));
        return documentBO;
    }
}
