package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import com.guardianesdelplaneta.domain.model.enums.AchievementConditionType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de respuesta para los datos de un logro")
public record AchievementResponseDto(
        @Schema(description = "Identificador único del logro", example = "1")
        Long id,
        
        @Schema(description = "Código único del logro", example = "FIRST_LOGIN")
        String code,
        
        @Schema(description = "Título del logro", example = "Primer Inicio de Sesión")
        String title,
        
        @Schema(description = "Descripción detallada del logro", example = "Inicia sesión por primera vez")
        String description,
        
        @Schema(description = "Tipo de condición para desbloquear el logro", example = "LOGIN_COUNT")
        AchievementConditionType conditionType,
        
        @Schema(description = "Valor requerido para cumplir la condición", example = "1")
        Integer conditionValue,
        
        @Schema(description = "Clave visual o icono asociado al logro", example = "badge_first_login")
        String visualKey,
        
        @Schema(description = "Indica si el logro está activo", example = "true")
        Boolean isActive
) {
}
