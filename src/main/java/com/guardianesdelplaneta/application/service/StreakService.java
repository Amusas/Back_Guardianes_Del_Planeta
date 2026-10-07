package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.DomainException;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.model.Streak;
import com.guardianesdelplaneta.domain.port.in.GetStreakUseCase;
import com.guardianesdelplaneta.domain.port.out.ChildProfilePersistencePort;
import com.guardianesdelplaneta.domain.port.out.StreakPersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StreakService implements GetStreakUseCase {

    private final StreakPersistencePort streakPort;
    private final ChildProfilePersistencePort childProfilePort;

    public StreakService(StreakPersistencePort streakPort, ChildProfilePersistencePort childProfilePort) {
        this.streakPort = streakPort;
        this.childProfilePort = childProfilePort;
    }

    @Override
    public Optional<Streak> getCurrentStreak(Long userId) {
        ChildProfile child = childProfilePort.findByUserId(userId)
                .orElseThrow(() -> new DomainException("Perfil de nino no encontrado para el usuario: " + userId));
        LocalDate today = LocalDate.now(ZoneId.of("America/Bogota"));
        return streakPort.findByChildIdAndActivityDate(child.getId(), today);
    }

    @Override
    public List<Streak> getStreakHistory(Long userId) {
        ChildProfile child = childProfilePort.findByUserId(userId)
                .orElseThrow(() -> new DomainException("Perfil de nino no encontrado para el usuario: " + userId));
        return streakPort.findByChildId(child.getId());
    }
}
