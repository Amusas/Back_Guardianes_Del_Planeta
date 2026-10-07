package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.RelationshipType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record GuardianChildResponseDto(
        @Schema(description = "Identificador único de la relación", example = "1")
        Long id,
        @Schema(description = "Identificador del acudiente", example = "1")
        Long guardianId,
        @Schema(description = "Identificador del niño", example = "2")
        Long childId,
        @Schema(description = "Tipo de relación", example = "PARENT")
        RelationshipType relationshipType,
        @Schema(description = "Indica si es el acudiente principal", example = "true")
        Boolean isPrimary,
        @Schema(description = "Fecha de creación del vínculo", example = "2023-10-27T10:00:00")
        LocalDateTime createdAt
) {
}
