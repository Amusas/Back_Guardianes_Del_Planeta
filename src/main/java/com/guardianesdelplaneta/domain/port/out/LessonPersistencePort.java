package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.Lesson;
import java.util.List;
import java.util.Optional;

public interface LessonPersistencePort {
    List<Lesson> findActiveLessonsByEcosystemId(Long ecosystemId);
    Optional<Lesson> findById(Long id);
}
