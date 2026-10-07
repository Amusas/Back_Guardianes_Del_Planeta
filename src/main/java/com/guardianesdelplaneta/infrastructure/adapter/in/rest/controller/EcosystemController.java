package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetEcosystemsUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.EcosystemResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.EcosystemRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ecosystems")
@Tag(name = "Ecosistemas", description = "Operaciones relacionadas con los ecosistemas")
public class EcosystemController {
    private final GetEcosystemsUseCase getEcosystemsUseCase;
    private final EcosystemRestMapper ecosystemRestMapper;

    public EcosystemController(GetEcosystemsUseCase getEcosystemsUseCase, EcosystemRestMapper ecosystemRestMapper) {
        this.getEcosystemsUseCase = getEcosystemsUseCase;
        this.ecosystemRestMapper = ecosystemRestMapper;
    }

    @Operation(summary = "Obtener ecosistemas activos")
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
    @GetMapping
    public ResponseEntity<List<EcosystemResponseDto>> getActiveEcosystems() {
        List<EcosystemResponseDto> response = getEcosystemsUseCase.getActiveEcosystems().stream()
                .map(ecosystemRestMapper::toResponseDto).toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener ecosistema por ID")
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
    @GetMapping("/{ecosystemId}")
    public ResponseEntity<EcosystemResponseDto> getEcosystemById(@PathVariable Long ecosystemId) {
        return ResponseEntity.ok(ecosystemRestMapper.toResponseDto(getEcosystemsUseCase.getEcosystemById(ecosystemId)));
    }

    @Operation(summary = "Obtener ecosistema por código")
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
    @GetMapping("/code/{code}")
    public ResponseEntity<EcosystemResponseDto> getEcosystemByCode(@PathVariable String code) {
        return ResponseEntity.ok(ecosystemRestMapper.toResponseDto(getEcosystemsUseCase.getEcosystemByCode(code)));
    }
}
