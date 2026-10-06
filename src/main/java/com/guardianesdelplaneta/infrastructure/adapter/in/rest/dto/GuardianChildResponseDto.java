package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.RelationshipType;

import java.time.LocalDateTime;

public record GuardianChildResponseDto(
        Long id,
        Long guardianId,
        Long childId,
        RelationshipType relationshipType,
        Boolean isPrimary,
        LocalDateTime createdAt
) {
}
