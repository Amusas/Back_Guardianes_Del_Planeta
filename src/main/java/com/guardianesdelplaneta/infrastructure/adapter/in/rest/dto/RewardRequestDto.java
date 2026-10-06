package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.RewardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RewardRequestDto(
        @NotBlank(message = "El nombre de la recompensa es obligatorio")
        String name,

        String description,

        @NotNull(message = "El tipo de recompensa es obligatorio")
        RewardType type,

        String visualKey,
        Long requiredLevelId,
        Boolean isActive
) {
}
