package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

public record ChildRewardResponseDto(
        Long id,
        Long childId,
        Long rewardId,
        LocalDateTime unlockedAt,
        Boolean isEquipped
) {
}
