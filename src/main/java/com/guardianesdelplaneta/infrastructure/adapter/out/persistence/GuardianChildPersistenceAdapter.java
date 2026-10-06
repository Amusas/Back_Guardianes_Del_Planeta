package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.out.GuardianChildPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildProfilePersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.GuardianChildRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuardianChildPersistenceAdapter implements GuardianChildPersistencePort {
    private final GuardianChildRepository repository;
    private final ChildProfilePersistenceMapper childMapper;

    public GuardianChildPersistenceAdapter(GuardianChildRepository repository, ChildProfilePersistenceMapper childMapper) {
        this.repository = repository;
        this.childMapper = childMapper;
    }

    @Override
    public List<ChildProfile> findChildrenByGuardianId(Long guardianId) {
        return repository.findByGuardianId(guardianId).stream().map(gc -> childMapper.toDomain(gc.getChild())).toList();
    }
}
