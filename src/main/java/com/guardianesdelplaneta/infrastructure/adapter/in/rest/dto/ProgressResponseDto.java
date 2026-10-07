package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ProgressResponseDto(
        @Schema(description = "Identificador único del progreso", example = "1")
        Long id,
        @Schema(description = "Identificador del perfil infantil", example = "10")
        Long childId,
        @Schema(description = "Identificador de la lección", example = "5")
        Long lessonId,
        @Schema(description = "Estado del progreso", example = "COMPLETED")
        ProgressStatus status,
        @Schema(description = "Porcentaje de progreso de la lección", example = "100")
        Integer progressPercentage,
        @Schema(description = "Fecha y hora de finalización", example = "2023-10-07T15:00:00")
        LocalDateTime completedAt,
        @Schema(description = "Fecha y hora de última actualización", example = "2023-10-07T15:00:00")
        LocalDateTime updatedAt
) {
}
