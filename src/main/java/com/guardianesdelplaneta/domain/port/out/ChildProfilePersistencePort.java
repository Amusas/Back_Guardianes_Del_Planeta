package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import java.util.Optional;

public interface ChildProfilePersistencePort {
    Optional<ChildProfile> findByUserId(Long userId);
    ChildProfile save(ChildProfile childProfile);
}
