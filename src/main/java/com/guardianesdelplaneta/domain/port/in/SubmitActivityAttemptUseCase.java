package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptResponseDto;

public interface SubmitActivityAttemptUseCase {
    ActivityAttemptResponseDto submitAttempt(Long userId, Long activityId, ActivityAttemptRequestDto request);
}
