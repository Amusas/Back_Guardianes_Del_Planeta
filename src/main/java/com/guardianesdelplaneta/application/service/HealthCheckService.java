package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.model.DatabaseHealth;
import com.guardianesdelplaneta.domain.port.in.CheckHealthUseCase;
import com.guardianesdelplaneta.domain.port.out.DatabaseHealthPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService implements CheckHealthUseCase {

    private static final Logger log = LoggerFactory.getLogger(HealthCheckService.class);

    private final DatabaseHealthPort databaseHealthPort;

    public HealthCheckService(DatabaseHealthPort databaseHealthPort) {
        this.databaseHealthPort = databaseHealthPort;
    }

    @Override
    public DatabaseHealth checkHealth() {
        log.info("Ejecutando caso de uso: Comprobación de salud de la base de datos.");
        return databaseHealthPort.pingDatabase();
    }
}
