package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Schema(description = "DTO para la creación de un perfil infantil")
public record ChildProfileCreateRequestDto(
        @Schema(description = "Identificador del usuario asociado al perfil", example = "1")
        @NotNull(message = "El identificador del usuario infantil es obligatorio")
        Long userId,

        @Schema(description = "Nombre del niño", example = "Juanito")
        @NotBlank(message = "El nombre del niño es obligatorio")
        String name,

        @Schema(description = "Fecha de nacimiento del niño", example = "2015-05-10")
        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe ser en el pasado")
        LocalDate dateOfBirth
) {
}
