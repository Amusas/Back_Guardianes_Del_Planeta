package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.in.GetGuardianChildrenUseCase;
import com.guardianesdelplaneta.domain.port.out.GuardianChildPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetGuardianChildrenService implements GetGuardianChildrenUseCase {
    private static final Logger log = LoggerFactory.getLogger(GetGuardianChildrenService.class);
    private final GuardianChildPersistencePort guardianChildPersistencePort;

    public GetGuardianChildrenService(GuardianChildPersistencePort guardianChildPersistencePort) {
        this.guardianChildPersistencePort = guardianChildPersistencePort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChildProfile> getChildrenByGuardianId(Long guardianId) {
        log.info("Fetching children for guardian user id: {}", guardianId);
        return guardianChildPersistencePort.findChildrenByGuardianId(guardianId);
    }
}
