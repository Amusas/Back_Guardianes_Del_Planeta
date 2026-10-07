package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Level;
import com.guardianesdelplaneta.domain.port.out.LevelPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.LevelPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LevelRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LevelPersistenceAdapter implements LevelPersistencePort {
    private final LevelRepository repository;
    private final LevelPersistenceMapper mapper;

    public LevelPersistenceAdapter(LevelRepository repository, LevelPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Level> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Level> findByLevelNumber(Integer levelNumber) {
        return repository.findByLevelNumber(levelNumber).map(mapper::toDomain);
    }
}
