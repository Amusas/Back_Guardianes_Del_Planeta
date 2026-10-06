package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import java.util.List;

public interface GetGuardianChildrenUseCase {
    List<ChildProfile> getChildrenByGuardianId(Long guardianId);
}

