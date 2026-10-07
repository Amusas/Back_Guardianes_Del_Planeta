package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.SubmitActivityAttemptUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/activities")
@Tag(name = "Intentos de Actividad", description = "Operaciones relacionadas con el envío de intentos para actividades educativas")
public class ActivityAttemptController {

    private final SubmitActivityAttemptUseCase submitActivityAttemptUseCase;

    public ActivityAttemptController(SubmitActivityAttemptUseCase submitActivityAttemptUseCase) {
        this.submitActivityAttemptUseCase = submitActivityAttemptUseCase;
    }

    @Operation(summary = "Enviar un intento de actividad", description = "Envía un nuevo intento para una actividad específica por el niño actual")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Intento enviado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros de solicitud inválidos", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Actividad no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/{activityId}/attempts")
    @PreAuthorize("hasRole('CHILD')")
    public ResponseEntity<ActivityAttemptResponseDto> submitAttempt(
            @PathVariable Long activityId,
            @Valid @RequestBody ActivityAttemptRequestDto request,
            Authentication authentication) {
        
        Long userId = (Long) authentication.getCredentials();
        ActivityAttemptResponseDto response = submitActivityAttemptUseCase.submitAttempt(userId, activityId, request);
        return ResponseEntity.ok(response);
    }
}
