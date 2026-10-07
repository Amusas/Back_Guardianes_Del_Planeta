package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @Schema(description = "Nombre de usuario", example = "juanito123")
        @NotBlank(message = "El nombre de usuario es obligatorio")
        String username,

        @Schema(description = "Contraseña del usuario", example = "passwordSeguro123")
        @NotBlank(message = "La contraseña es obligatoria")
        String password
) {
}
