package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.domain.port.out.LessonPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.LessonPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LessonRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LessonPersistenceAdapter implements LessonPersistencePort {
    private final LessonRepository repository;
    private final LessonPersistenceMapper mapper;

    public LessonPersistenceAdapter(LessonRepository repository, LessonPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Lesson> findActiveLessonsByEcosystemId(Long ecosystemId) {
        return repository.findByEcosystemIdAndIsActiveTrueOrderByOrderIndexAsc(ecosystemId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Lesson> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
