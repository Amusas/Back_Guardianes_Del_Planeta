package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.GuardianChildDashboardDto;

public interface GetGuardianChildDashboardUseCase {
    GuardianChildDashboardDto getChildDashboard(Long guardianUserId, Long childId);
}
