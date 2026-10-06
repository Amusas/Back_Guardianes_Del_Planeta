package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.UserRole;
import com.guardianesdelplaneta.domain.model.enums.UserStatus;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String username,
        UserRole role,
        UserStatus status,
        LocalDateTime createdAt
) {
}
