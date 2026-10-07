package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.Streak;
import java.time.LocalDate;
import java.util.Optional;

public interface StreakPersistencePort {
    Optional<Streak> findByChildIdAndActivityDate(Long childId, LocalDate date);
    Streak save(Streak streak);
}
