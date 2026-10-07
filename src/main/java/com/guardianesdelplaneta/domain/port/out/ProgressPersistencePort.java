package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.Progress;
import java.util.Optional;
import java.util.List;

public interface ProgressPersistencePort {
    Optional<Progress> findByChildIdAndLessonId(Long childId, Long lessonId);
    List<Progress> findByChildId(Long childId);
    Progress save(Progress progress);
}
