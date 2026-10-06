package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.UserRole;

public record AuthResponseDto(
        String token,
        String tokenType,
        String username,
        UserRole role,
        Long userId,
        long expiresInMs
) {
}
