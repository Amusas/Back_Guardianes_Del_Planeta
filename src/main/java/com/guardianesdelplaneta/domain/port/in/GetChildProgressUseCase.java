package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.Progress;

import java.util.List;

public interface GetChildProgressUseCase {
    List<Progress> getProgressByUserId(Long userId);
    List<Long> getCompletedLessonIdsByUserId(Long userId);
}
