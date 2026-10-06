package com.guardianesdelplaneta.domain.model;

import com.guardianesdelplaneta.domain.model.enums.XPTransactionType;

import java.time.LocalDateTime;

public class XPTransaction {

    private Long id;
    private Long childId;
    private Integer amount;
    private XPTransactionType type;
    private Long referenceId;
    private String description;
    private LocalDateTime createdAt;

    public XPTransaction() {
    }

    public XPTransaction(Long id, Long childId, Integer amount, XPTransactionType type, Long referenceId, String description, LocalDateTime createdAt) {
        this.id = id;
        this.childId = childId;
        this.amount = amount;
        this.type = type;
        this.referenceId = referenceId;
        this.description = description;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public XPTransactionType getType() {
        return type;
    }

    public void setType(XPTransactionType type) {
        this.type = type;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
