package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.port.in.GetEcosystemsUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.EcosystemResponseDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.EcosystemRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ecosystems")
public class EcosystemController {
    private final GetEcosystemsUseCase getEcosystemsUseCase;
    private final EcosystemRestMapper ecosystemRestMapper;

    public EcosystemController(GetEcosystemsUseCase getEcosystemsUseCase, EcosystemRestMapper ecosystemRestMapper) {
        this.getEcosystemsUseCase = getEcosystemsUseCase;
        this.ecosystemRestMapper = ecosystemRestMapper;
    }

    @GetMapping
    public ResponseEntity<List<EcosystemResponseDto>> getActiveEcosystems() {
        List<EcosystemResponseDto> response = getEcosystemsUseCase.getActiveEcosystems().stream()
                .map(ecosystemRestMapper::toResponseDto).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{ecosystemId}")
    public ResponseEntity<EcosystemResponseDto> getEcosystemById(@PathVariable Long ecosystemId) {
        return ResponseEntity.ok(ecosystemRestMapper.toResponseDto(getEcosystemsUseCase.getEcosystemById(ecosystemId)));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<EcosystemResponseDto> getEcosystemByCode(@PathVariable String code) {
        return ResponseEntity.ok(ecosystemRestMapper.toResponseDto(getEcosystemsUseCase.getEcosystemByCode(code)));
    }
}
