package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.RelationshipType;
import jakarta.validation.constraints.NotNull;

public record GuardianChildLinkRequestDto(
        @NotNull(message = "El identificador del acudiente es obligatorio")
        Long guardianId,

        @NotNull(message = "El identificador del perfil infantil es obligatorio")
        Long childId,

        @NotNull(message = "El tipo de parentesco es obligatorio")
        RelationshipType relationshipType,

        Boolean isPrimary
) {
}
