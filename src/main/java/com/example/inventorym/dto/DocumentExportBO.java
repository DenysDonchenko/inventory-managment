package com.example.inventorym.dto;

import com.example.inventorym.entity.enums.Action;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class DocumentExportBO {

    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    private BigDecimal totalCount;
    private Set<Action> typeDocument;
    private List<ProductExportBO> productExportBOS;
    private String docNumber;

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

    public List<ProductExportBO> getProductExportBOS() {
        return productExportBOS;
    }

    public void setProductExportBOS(List<ProductExportBO> productExportBOS) {
        this.productExportBOS = productExportBOS;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public BigDecimal getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(BigDecimal totalCount) {
        this.totalCount = totalCount;
    }
}
