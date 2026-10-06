package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.AchievementConditionType;

public record AchievementResponseDto(
        Long id,
        String code,
        String title,
        String description,
        AchievementConditionType conditionType,
        Integer conditionValue,
        String visualKey,
        Boolean isActive
) {
}
