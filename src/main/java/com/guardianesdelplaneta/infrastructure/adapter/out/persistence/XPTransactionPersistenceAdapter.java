package com.guardianesdelplaneta.infrastructure.adapter.out.persistence;

import com.guardianesdelplaneta.domain.model.XPTransaction;
import com.guardianesdelplaneta.domain.model.enums.XPTransactionType;
import com.guardianesdelplaneta.domain.port.out.XPTransactionPersistencePort;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.XPTransactionPersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.XPTransactionRepository;
import org.springframework.stereotype.Component;

@Component
public class XPTransactionPersistenceAdapter implements XPTransactionPersistencePort {
    private final XPTransactionRepository repository;
    private final XPTransactionPersistenceMapper mapper;

    public XPTransactionPersistenceAdapter(XPTransactionRepository repository, XPTransactionPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public XPTransaction save(XPTransaction transaction) {
        return mapper.toDomain(repository.save(mapper.toEntity(transaction)));
    }

    @Override
    public boolean existsByChildIdAndTypeAndReferenceId(Long childId, XPTransactionType type, Long referenceId) {
        return repository.existsByChildIdAndTypeAndReferenceId(childId, type, referenceId);
    }
}
