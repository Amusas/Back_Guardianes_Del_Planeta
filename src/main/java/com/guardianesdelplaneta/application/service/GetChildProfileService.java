package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.ChildProfileNotFoundException;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.in.GetChildProfileUseCase;
import com.guardianesdelplaneta.domain.port.out.ChildProfilePersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetChildProfileService implements GetChildProfileUseCase {
    private static final Logger log = LoggerFactory.getLogger(GetChildProfileService.class);
    private final ChildProfilePersistencePort childProfilePersistencePort;

    public GetChildProfileService(ChildProfilePersistencePort childProfilePersistencePort) {
        this.childProfilePersistencePort = childProfilePersistencePort;
    }

    @Override
    @Transactional(readOnly = true)
    public ChildProfile getChildProfileByUserId(Long userId) {
        log.info("Fetching child profile for user id: {}", userId);
        return childProfilePersistencePort.findByUserId(userId)
                .orElseThrow(() -> new ChildProfileNotFoundException("Child profile not found for user id " + userId));
    }
}
