package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.Level;
import java.util.Optional;

public interface LevelPersistencePort {
    Optional<Level> findById(Long id);
    Optional<Level> findByLevelNumber(Integer levelNumber);
}
