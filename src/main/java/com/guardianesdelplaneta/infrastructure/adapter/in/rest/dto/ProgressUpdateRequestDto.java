package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProgressUpdateRequestDto(
        @Schema(description = "Identificador del perfil infantil", example = "10")
        @NotNull(message = "El identificador del perfil infantil es obligatorio")
        Long childId,

        @Schema(description = "Identificador de la lección", example = "5")
        @NotNull(message = "El identificador de la lección es obligatorio")
        Long lessonId,

        @Schema(description = "Estado del progreso", example = "IN_PROGRESS")
        @NotNull(message = "El estado del progreso es obligatorio")
        ProgressStatus status,

        @Schema(description = "Porcentaje de progreso de la lección", example = "50")
        @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
        @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
        Integer progressPercentage
) {
}
