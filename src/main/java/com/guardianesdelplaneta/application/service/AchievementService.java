package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.AchievementNotFoundException;
import com.guardianesdelplaneta.domain.exception.DomainException;
import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.in.GetAchievementsUseCase;
import com.guardianesdelplaneta.domain.port.out.AchievementPersistencePort;
import com.guardianesdelplaneta.domain.port.out.ChildAchievementPersistencePort;
import com.guardianesdelplaneta.domain.port.out.ChildProfilePersistencePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AchievementService implements GetAchievementsUseCase {

    private final AchievementPersistencePort achievementPort;
    private final ChildAchievementPersistencePort childAchievementPort;
    private final ChildProfilePersistencePort childProfilePort;

    public AchievementService(AchievementPersistencePort achievementPort, ChildAchievementPersistencePort childAchievementPort, ChildProfilePersistencePort childProfilePort) {
        this.achievementPort = achievementPort;
        this.childAchievementPort = childAchievementPort;
        this.childProfilePort = childProfilePort;
    }

    @Override
    public List<Achievement> getAllActiveAchievements() {
        return achievementPort.findAllActive();
    }

    @Override
    public List<Achievement> getUnlockedAchievements(Long userId) {
        ChildProfile child = childProfilePort.findByUserId(userId)
                .orElseThrow(() -> new DomainException("Perfil de nino no encontrado para el usuario: " + userId));
        
        List<ChildAchievement> unlocked = childAchievementPort.findByChildId(child.getId());
        return unlocked.stream()
                .map(ca -> achievementPort.findById(ca.getAchievementId()).orElseThrow(() -> new AchievementNotFoundException("Achievement not found: " + ca.getAchievementId())))
                .collect(Collectors.toList());
    }

    @Override
    public ChildAchievement getAchievementStatus(Long userId, Long achievementId) {
        ChildProfile child = childProfilePort.findByUserId(userId)
                .orElseThrow(() -> new DomainException("Perfil de nino no encontrado para el usuario: " + userId));
                
        return childAchievementPort.findByChildIdAndAchievementId(child.getId(), achievementId)
                .orElseThrow(() -> new AchievementNotFoundException("El logro " + achievementId + " no ha sido desbloqueado por el nino."));
    }
}
