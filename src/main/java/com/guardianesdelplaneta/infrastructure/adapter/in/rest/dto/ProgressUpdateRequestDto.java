package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProgressUpdateRequestDto(
        @NotNull(message = "El identificador del perfil infantil es obligatorio")
        Long childId,

        @NotNull(message = "El identificador de la lección es obligatorio")
        Long lessonId,

        @NotNull(message = "El estado del progreso es obligatorio")
        ProgressStatus status,

        @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
        @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
        Integer progressPercentage
) {
}
