package com.example.inventorym.dto;

import com.example.inventorym.entity.enums.Action;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class DocumentBO {

    private UUID documentId;
    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    private Set<Action> typeDocument;
    private BigDecimal totalCount;
    private String docNumber;
    private boolean isSale;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Action> getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(Set<Action> typeDocument) {
        this.typeDocument = typeDocument;
    }

    public BigDecimal getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(BigDecimal totalCount) {
        this.totalCount = totalCount;
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public void setDocumentId(UUID documentId) {
        this.documentId = documentId;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public boolean getSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }
}
