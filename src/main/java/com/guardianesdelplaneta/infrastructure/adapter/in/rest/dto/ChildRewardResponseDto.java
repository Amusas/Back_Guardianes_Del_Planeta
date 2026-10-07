package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record ChildRewardResponseDto(
        @Schema(description = "Identificador único de la recompensa del niño", example = "1")
        Long id,
        @Schema(description = "Identificador del niño", example = "10")
        Long childId,
        @Schema(description = "Identificador de la recompensa", example = "5")
        Long rewardId,
        @Schema(description = "Fecha y hora en que se desbloqueó la recompensa", example = "2023-10-27T10:00:00")
        LocalDateTime unlockedAt,
        @Schema(description = "Indica si la recompensa está equipada", example = "true")
        Boolean isEquipped
) {
}
