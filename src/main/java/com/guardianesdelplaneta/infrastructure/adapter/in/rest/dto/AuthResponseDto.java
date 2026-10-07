package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de respuesta de autenticación")
public record AuthResponseDto(
        @Schema(description = "Token de autenticación JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token,
        
        @Schema(description = "Tipo de token", example = "Bearer")
        String tokenType,
        
        @Schema(description = "Nombre de usuario autenticado", example = "usuario123")
        String username,
        
        @Schema(description = "Rol del usuario", example = "CHILD")
        UserRole role,
        
        @Schema(description = "Identificador del usuario", example = "1")
        Long userId,
        
        @Schema(description = "Tiempo de expiración del token en milisegundos", example = "3600000")
        long expiresInMs
) {
}
