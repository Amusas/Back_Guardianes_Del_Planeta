package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.Lesson;
import java.util.List;

public interface GetLessonsUseCase {
    List<Lesson> getActiveLessonsByEcosystemId(Long ecosystemId);
    Lesson getLessonById(Long id);
}
