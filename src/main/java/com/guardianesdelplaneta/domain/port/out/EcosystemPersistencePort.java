package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.Ecosystem;
import java.util.List;
import java.util.Optional;

public interface EcosystemPersistencePort {
    List<Ecosystem> findActiveEcosystems();
    Optional<Ecosystem> findById(Long id);
    Optional<Ecosystem> findByCode(String code);
}
