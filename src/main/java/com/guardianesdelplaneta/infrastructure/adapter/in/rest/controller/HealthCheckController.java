package com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller;

import com.guardianesdelplaneta.domain.model.DatabaseHealth;
import com.guardianesdelplaneta.domain.port.in.CheckHealthUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.HealthResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    private static final Logger log = LoggerFactory.getLogger(HealthCheckController.class);

    private final CheckHealthUseCase checkHealthUseCase;

    public HealthCheckController(CheckHealthUseCase checkHealthUseCase) {
        this.checkHealthUseCase = checkHealthUseCase;
    }

    @GetMapping
    public ResponseEntity<HealthResponseDto> checkHealth() {
        log.info("Endpoint GET /health invocado.");
        DatabaseHealth dbHealth = checkHealthUseCase.checkHealth();

        HealthResponseDto response = new HealthResponseDto(
                "Guardianes del Planeta API",
                dbHealth.isConnected() ? "UP" : "DOWN",
                dbHealth.isConnected(),
                dbHealth.getDatabaseName(),
                dbHealth.getDatabaseVersion(),
                dbHealth.getMessage(),
                LocalDateTime.now()
        );

        if (dbHealth.isConnected()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }
}
