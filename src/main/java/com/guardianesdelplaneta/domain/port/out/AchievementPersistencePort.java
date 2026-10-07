package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.Achievement;
import java.util.List;
import java.util.Optional;

public interface AchievementPersistencePort {
    List<Achievement> findAllActive();
    Optional<Achievement> findById(Long id);
    Optional<Achievement> findByCode(String code);
}
