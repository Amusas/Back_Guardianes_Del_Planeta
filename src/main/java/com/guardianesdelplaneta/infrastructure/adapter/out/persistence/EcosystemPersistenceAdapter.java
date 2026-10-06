package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Ecosystem;
import com.guardianesdelplaneta.domain.port.out.EcosystemPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.EcosystemPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.EcosystemRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EcosystemPersistenceAdapter implements EcosystemPersistencePort {
    private final EcosystemRepository repository;
    private final EcosystemPersistenceMapper mapper;

    public EcosystemPersistenceAdapter(EcosystemRepository repository, EcosystemPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Ecosystem> findActiveEcosystems() {
        return repository.findByIsActiveTrue().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Ecosystem> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Ecosystem> findByCode(String code) {
        return repository.findByCode(code).map(mapper::toDomain);
    }
}
