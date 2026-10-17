package com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Evento reciente en la cronología de aprendizaje del niño")
public record RecentActivityDto(
        @Schema(description = "Título o nombre de la actividad o logro", example = "El ciclo del agua")
        String title,

        @Schema(description = "Detalle o resultado", example = "Respuesta correcta")
        String description,

        @Schema(description = "Tipo de actividad: ACTIVITY, ACHIEVEMENT, LEVEL_UP", example = "ACTIVITY")
        String type,

        @Schema(description = "XP obtenida en este evento", example = "50")
        Integer xpAwarded,

        @Schema(description = "Fecha y hora del evento", example = "2026-10-15T10:30:00")
        LocalDateTime timestamp,

        @Schema(description = "Indica si la acción fue exitosa o correcta", example = "true")
        Boolean isSuccess
) {
}
