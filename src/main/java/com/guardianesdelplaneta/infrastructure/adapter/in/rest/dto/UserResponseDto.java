package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.UserRole;
import com.guardianesdelplaneta.domain.model.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UserResponseDto(
        @Schema(description = "Identificador único del usuario", example = "1")
        Long id,
        @Schema(description = "Nombre de usuario", example = "juanito123")
        String username,
        @Schema(description = "Rol del usuario", example = "CHILD")
        UserRole role,
        @Schema(description = "Estado del usuario", example = "ACTIVE")
        UserStatus status,
        @Schema(description = "Fecha y hora de creación", example = "2023-10-07T15:00:00")
        LocalDateTime createdAt
) {
}
