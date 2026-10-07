package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.Progress;
import com.guardianesdelplaneta.domain.port.out.ProgressPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ProgressPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ProgressRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
public class ProgressPersistenceAdapter implements ProgressPersistencePort {
    private final ProgressRepository repository;
    private final ProgressPersistenceMapper mapper;

    public ProgressPersistenceAdapter(ProgressRepository repository, ProgressPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Progress> findByChildIdAndLessonId(Long childId, Long lessonId) {
        return repository.findByChildIdAndLessonId(childId, lessonId).map(mapper::toDomain);
    }

    @Override
    public List<Progress> findByChildId(Long childId) {
        return repository.findByChildId(childId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Progress save(Progress progress) {
        return mapper.toDomain(repository.save(mapper.toEntity(progress)));
    }
}
