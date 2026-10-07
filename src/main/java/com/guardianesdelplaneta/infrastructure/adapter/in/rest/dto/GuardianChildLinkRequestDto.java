package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.RelationshipType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record GuardianChildLinkRequestDto(
        @NotNull(message = "El identificador del acudiente es obligatorio")
        @Schema(description = "Identificador del acudiente (guardián)", example = "1")
        Long guardianId,

        @NotNull(message = "El identificador del perfil infantil es obligatorio")
        @Schema(description = "Identificador del niño", example = "2")
        Long childId,

        @NotNull(message = "El tipo de parentesco es obligatorio")
        @Schema(description = "Tipo de relación entre el acudiente y el niño", example = "PARENT")
        RelationshipType relationshipType,

        @Schema(description = "Indica si es el acudiente principal", example = "true")
        Boolean isPrimary
) {
}
