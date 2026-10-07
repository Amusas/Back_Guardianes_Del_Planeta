package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.Streak;
import java.util.List;
import java.util.Optional;

public interface GetStreakUseCase {
    Optional<Streak> getCurrentStreak(Long userId);
    List<Streak> getStreakHistory(Long userId);
}
