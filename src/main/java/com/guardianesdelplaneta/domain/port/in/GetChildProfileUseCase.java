package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.ChildProfile;

public interface GetChildProfileUseCase {
    ChildProfile getChildProfileByUserId(Long userId);
}
