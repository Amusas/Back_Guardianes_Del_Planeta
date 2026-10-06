package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.AchievementConditionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AchievementRequestDto(
        @NotBlank(message = "El código del logro es obligatorio")
        String code,

        @NotBlank(message = "El título del logro es obligatorio")
        String title,

        String description,

        @NotNull(message = "El tipo de condición es obligatorio")
        AchievementConditionType conditionType,

        @NotNull(message = "El valor de la condición es obligatorio")
        Integer conditionValue,

        String visualKey,
        Boolean isActive
) {
}
