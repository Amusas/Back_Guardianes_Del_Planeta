package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record ChildProfileCreateRequestDto(
        @NotNull(message = "El identificador del usuario infantil es obligatorio")
        Long userId,

        @NotBlank(message = "El nombre del niño es obligatorio")
        String name,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe ser en el pasado")
        LocalDate dateOfBirth
) {
}
