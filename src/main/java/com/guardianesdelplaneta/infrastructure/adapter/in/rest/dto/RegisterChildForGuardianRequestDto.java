package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "DTO para la creación y vinculación de un perfil infantil por su acudiente")
public record RegisterChildForGuardianRequestDto(
        @Schema(description = "Nombre de usuario único para el niño", example = "juanito_eco")
        @NotBlank(message = "El nombre de usuario es obligatorio")
        @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
        String username,

        @Schema(description = "Contraseña de acceso del niño", example = "clave123")
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password,

        @Schema(description = "Nombre del niño", example = "Juanito")
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
        String name,

        @Schema(description = "Fecha de nacimiento del niño", example = "2016-05-15")
        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
        LocalDate dateOfBirth
) {
}
