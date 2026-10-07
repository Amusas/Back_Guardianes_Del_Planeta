package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.Activity;
import java.util.Optional;

public interface ActivityPersistencePort {
    Optional<Activity> findById(Long id);
}
