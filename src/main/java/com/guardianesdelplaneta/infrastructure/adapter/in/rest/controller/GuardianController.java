package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetGuardianChildrenUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildProfileResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.ChildProfileRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guardians")
@Tag(name = "Guardianes", description = "Operaciones relacionadas con los guardianes")
public class GuardianController {
    private final GetGuardianChildrenUseCase getGuardianChildrenUseCase;
    private final ChildProfileRestMapper childProfileRestMapper;

    public GuardianController(GetGuardianChildrenUseCase getGuardianChildrenUseCase, ChildProfileRestMapper childProfileRestMapper) {
        this.getGuardianChildrenUseCase = getGuardianChildrenUseCase;
        this.childProfileRestMapper = childProfileRestMapper;
    }

    @Operation(summary = "Obtener hijos del guardián")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "201", description = "Creado"),
            @ApiResponse(responseCode = "400", description = "Petición incorrecta"),
            @ApiResponse(responseCode = "401", description = "No autorizado"),
            @ApiResponse(responseCode = "403", description = "Prohibido"),
            @ApiResponse(responseCode = "404", description = "No encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflicto"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me/children")
    @PreAuthorize("hasRole('GUARDIAN')")
    public ResponseEntity<List<ChildProfileResponseDto>> getMyChildren() {
        Object credentials = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        Long userId;
        userId = credentials instanceof Number ? ((Number) credentials).longValue() : (Long) credentials;
        List<ChildProfileResponseDto> response = getGuardianChildrenUseCase.getChildrenByGuardianId(userId).stream()
                .map(childProfileRestMapper::toResponseDto).toList();
        return ResponseEntity.ok(response);
    }
}


