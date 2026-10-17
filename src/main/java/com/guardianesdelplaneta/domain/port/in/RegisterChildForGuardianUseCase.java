package com.guardianesdelplaneta.domain.port.in;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import java.time.LocalDate;

public interface RegisterChildForGuardianUseCase {
    ChildProfile registerChildForGuardian(Long guardianUserId, String username, String password, String name, LocalDate dateOfBirth);
}
