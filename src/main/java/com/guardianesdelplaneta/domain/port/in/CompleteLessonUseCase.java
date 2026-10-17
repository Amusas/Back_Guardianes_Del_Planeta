package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.Progress;

public interface CompleteLessonUseCase {
    Progress completeLesson(Long userId, Long lessonId);
}
