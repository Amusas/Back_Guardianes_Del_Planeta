package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

public record LevelResponseDto(
        Long id,
        Integer levelNumber,
        String name,
        Integer requiredXp,
        String description,
        String visualKey
) {
}
