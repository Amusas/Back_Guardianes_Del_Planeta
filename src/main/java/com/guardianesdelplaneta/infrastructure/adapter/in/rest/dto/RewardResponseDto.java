package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.RewardType;

public record RewardResponseDto(
        Long id,
        String name,
        String description,
        RewardType type,
        String visualKey,
        Long requiredLevelId,
        Boolean isActive
) {
}
