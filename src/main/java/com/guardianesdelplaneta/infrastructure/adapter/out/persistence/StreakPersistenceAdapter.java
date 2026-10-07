package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.domain.port.out.StreakPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.StreakPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.StreakRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class StreakPersistenceAdapter implements StreakPersistencePort {
    private final StreakRepository repository;
    private final StreakPersistenceMapper mapper;

    public StreakPersistenceAdapter(StreakRepository repository, StreakPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Streak> findByChildIdAndActivityDate(Long childId, LocalDate date) {
        return repository.findByChildIdAndActivityDate(childId, date).map(mapper::toDomain);
    }

    @Override
    public java.util.List<Streak> findByChildId(Long childId) {
        return repository.findByChildId(childId).stream()
                .map(mapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Streak save(Streak streak) {
        return mapper.toDomain(repository.save(mapper.toEntity(streak)));
    }
}


