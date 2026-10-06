package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.EcosystemNotFoundException;
import com.guardianesdelplaneta.domain.model.Ecosystem;
import com.guardianesdelplaneta.domain.port.in.GetEcosystemsUseCase;
import com.guardianesdelplaneta.domain.port.out.EcosystemPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetEcosystemsService implements GetEcosystemsUseCase {
    private static final Logger log = LoggerFactory.getLogger(GetEcosystemsService.class);
    private final EcosystemPersistencePort ecosystemPersistencePort;

    public GetEcosystemsService(EcosystemPersistencePort ecosystemPersistencePort) {
        this.ecosystemPersistencePort = ecosystemPersistencePort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ecosystem> getActiveEcosystems() {
        log.info("Fetching active ecosystems");
        return ecosystemPersistencePort.findActiveEcosystems();
    }

    @Override
    @Transactional(readOnly = true)
    public Ecosystem getEcosystemById(Long id) {
        log.info("Fetching ecosystem by id: {}", id);
        return ecosystemPersistencePort.findById(id)
                .orElseThrow(() -> new EcosystemNotFoundException("Ecosystem with id " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Ecosystem getEcosystemByCode(String code) {
        log.info("Fetching ecosystem by code: {}", code);
        return ecosystemPersistencePort.findByCode(code)
                .orElseThrow(() -> new EcosystemNotFoundException("Ecosystem with code " + code + " not found"));
    }
}
