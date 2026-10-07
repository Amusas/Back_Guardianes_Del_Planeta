package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.RewardType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RewardRequestDto(
        @Schema(description = "Nombre de la recompensa", example = "Medalla de reciclaje")
        @NotBlank(message = "El nombre de la recompensa es obligatorio")
        String name,

        @Schema(description = "Descripción de la recompensa", example = "Otorgado por reciclar 10 objetos")
        String description,

        @Schema(description = "Tipo de recompensa", example = "BADGE")
        @NotNull(message = "El tipo de recompensa es obligatorio")
        RewardType type,

        @Schema(description = "Clave visual de la recompensa", example = "badge_recycle")
        String visualKey,
        @Schema(description = "Identificador del nivel requerido", example = "2")
        Long requiredLevelId,
        @Schema(description = "Indica si la recompensa está activa", example = "true")
        Boolean isActive
) {
}
