package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.Activity;
import java.util.List;

public interface GetActivitiesUseCase {
    List<Activity> getActivitiesByLessonId(Long lessonId);
}
