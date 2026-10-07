package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.RewardType;
import io.swagger.v3.oas.annotations.media.Schema;

public record RewardResponseDto(
        @Schema(description = "Identificador único de la recompensa", example = "1")
        Long id,
        @Schema(description = "Nombre de la recompensa", example = "Medalla de reciclaje")
        String name,
        @Schema(description = "Descripción de la recompensa", example = "Otorgado por reciclar 10 objetos")
        String description,
        @Schema(description = "Tipo de recompensa", example = "BADGE")
        RewardType type,
        @Schema(description = "Clave visual de la recompensa", example = "badge_recycle")
        String visualKey,
        @Schema(description = "Identificador del nivel requerido", example = "2")
        Long requiredLevelId,
        @Schema(description = "Indica si la recompensa está activa", example = "true")
        Boolean isActive
) {
}
