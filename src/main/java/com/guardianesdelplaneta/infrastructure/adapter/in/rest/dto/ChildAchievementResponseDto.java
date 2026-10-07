package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "DTO de respuesta para un logro obtenido por un niño")
public record ChildAchievementResponseDto(
        @Schema(description = "Identificador único del logro obtenido", example = "1")
        Long id,
        
        @Schema(description = "Identificador del perfil del niño", example = "1")
        Long childId,
        
        @Schema(description = "Identificador del logro", example = "10")
        Long achievementId,
        
        @Schema(description = "Fecha y hora en que se desbloqueó el logro", example = "2023-10-01T10:00:00")
        LocalDateTime unlockedAt
) {
}
