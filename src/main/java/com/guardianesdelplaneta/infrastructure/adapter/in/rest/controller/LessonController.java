package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetLessonsUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.LessonResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.LessonRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LessonController {
    private final GetLessonsUseCase getLessonsUseCase;
    private final LessonRestMapper lessonRestMapper;

    public LessonController(GetLessonsUseCase getLessonsUseCase, LessonRestMapper lessonRestMapper) {
        this.getLessonsUseCase = getLessonsUseCase;
        this.lessonRestMapper = lessonRestMapper;
    }

    @GetMapping("/ecosystems/{ecosystemId}/lessons")
    public ResponseEntity<List<LessonResponseDto>> getActiveLessons(@PathVariable Long ecosystemId) {
        List<LessonResponseDto> response = getLessonsUseCase.getActiveLessonsByEcosystemId(ecosystemId).stream()
                .map(lessonRestMapper::toResponseDto).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<LessonResponseDto> getLessonById(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonRestMapper.toResponseDto(getLessonsUseCase.getLessonById(lessonId)));
    }
}
