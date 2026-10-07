package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.AchievementConditionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para la solicitud de creación o actualización de un logro")
public record AchievementRequestDto(
        @Schema(description = "Código único del logro", example = "FIRST_LOGIN")
        @NotBlank(message = "El código del logro es obligatorio")
        String code,

        @Schema(description = "Título del logro", example = "Primer Inicio de Sesión")
        @NotBlank(message = "El título del logro es obligatorio")
        String title,

        @Schema(description = "Descripción detallada del logro", example = "Inicia sesión por primera vez")
        String description,

        @Schema(description = "Tipo de condición para desbloquear el logro", example = "LOGIN_COUNT")
        @NotNull(message = "El tipo de condición es obligatorio")
        AchievementConditionType conditionType,

        @Schema(description = "Valor requerido para cumplir la condición", example = "1")
        @NotNull(message = "El valor de la condición es obligatorio")
        Integer conditionValue,

        @Schema(description = "Clave visual o icono asociado al logro", example = "badge_first_login")
        String visualKey,
        
        @Schema(description = "Indica si el logro está activo", example = "true")
        Boolean isActive
) {
}
