package com.guardianesdelplaneta.domain.port.out;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import java.util.List;

public interface GuardianChildPersistencePort {
    List<ChildProfile> findChildrenByGuardianId(Long guardianId);
}
