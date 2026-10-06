package com.guardianesdelplaneta.domain.model;

import com.guardianesdelplaneta.domain.model.enums.RelationshipType;

import java.time.LocalDateTime;

public class GuardianChild {

    private Long id;
    private Long guardianId;
    private Long childId;
    private RelationshipType relationshipType;
    private Boolean isPrimary;
    private LocalDateTime createdAt;

    public GuardianChild() {
    }

    public GuardianChild(Long id, Long guardianId, Long childId, RelationshipType relationshipType, Boolean isPrimary, LocalDateTime createdAt) {
        this.id = id;
        this.guardianId = guardianId;
        this.childId = childId;
        this.relationshipType = relationshipType;
        this.isPrimary = isPrimary != null ? isPrimary : false;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(Long guardianId) {
        this.guardianId = guardianId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
