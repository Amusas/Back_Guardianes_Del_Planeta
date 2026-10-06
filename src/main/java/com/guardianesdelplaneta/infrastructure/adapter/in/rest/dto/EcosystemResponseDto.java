package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

public record EcosystemResponseDto(
        Long id,
        String code,
        String name,
        String description,
        Long requiredLevelId,
        String mascotKey,
        Boolean isActive
) {
}
