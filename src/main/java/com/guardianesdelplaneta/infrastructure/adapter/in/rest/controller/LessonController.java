package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetLessonsUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.LessonResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.LessonRestMapper;
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
@RequestMapping("/api/v1")
@Tag(name = "Lecciones", description = "Operaciones relacionadas con las lecciones")
public class LessonController {
    private final GetLessonsUseCase getLessonsUseCase;
    private final LessonRestMapper lessonRestMapper;

    public LessonController(GetLessonsUseCase getLessonsUseCase, LessonRestMapper lessonRestMapper) {
        this.getLessonsUseCase = getLessonsUseCase;
        this.lessonRestMapper = lessonRestMapper;
    }

    @Operation(summary = "Obtener lecciones activas para un ecosistema")
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
    @GetMapping("/ecosystems/{ecosystemId}/lessons")
    public ResponseEntity<List<LessonResponseDto>> getActiveLessons(@PathVariable Long ecosystemId) {
        List<LessonResponseDto> response = getLessonsUseCase.getActiveLessonsByEcosystemId(ecosystemId).stream()
                .map(lessonRestMapper::toResponseDto).toList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener lección por ID")
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
    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<LessonResponseDto> getLessonById(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonRestMapper.toResponseDto(getLessonsUseCase.getLessonById(lessonId)));
    }
}
