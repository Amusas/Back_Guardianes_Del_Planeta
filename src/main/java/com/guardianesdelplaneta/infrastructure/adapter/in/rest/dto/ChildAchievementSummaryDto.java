package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Resumen de un logro desbloqueado por el niño")
public record ChildAchievementSummaryDto(
        @Schema(description = "Identificador del logro", example = "1")
        Long id,

        @Schema(description = "Título del logro", example = "Primeros Pasos")
        String title,

        @Schema(description = "Descripción del logro", example = "Completaste tu primera lección")
        String description,

        @Schema(description = "Clave visual o icono de la insignia", example = "FIRST_LESSON")
        String badgeKey,

        @Schema(description = "Fecha y hora de desbloqueo", example = "2026-10-15T10:30:00")
        LocalDateTime unlockedAt
) {
}
