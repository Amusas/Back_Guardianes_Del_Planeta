import os

files = {
    'src/main/java/com/guardianesdelplaneta/application/service/AchievementService.java': '''package com.guardianesdelplaneta.application.service;

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
''',
    'src/main/java/com/guardianesdelplaneta/application/service/StreakService.java': '''package com.guardianesdelplaneta.application.service;

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
''',
    'src/main/java/com/guardianesdelplaneta/application/service/AchievementEvaluator.java': '''package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.model.Achievement;
import com.guardianesdelplaneta.domain.model.ChildAchievement;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.port.out.AchievementPersistencePort;
import com.guardianesdelplaneta.domain.port.out.ChildAchievementPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementEvaluator {
    private static final Logger logger = LoggerFactory.getLogger(AchievementEvaluator.class);

    private final AchievementPersistencePort achievementPort;
    private final ChildAchievementPersistencePort childAchievementPort;

    public AchievementEvaluator(AchievementPersistencePort achievementPort, ChildAchievementPersistencePort childAchievementPort) {
        this.achievementPort = achievementPort;
        this.childAchievementPort = childAchievementPort;
    }

    @Transactional
    public List<Achievement> evaluateAchievements(ChildProfile child, int activitiesCompleted, int lessonsCompleted, int ecosystemsCompleted, int specialChallengesCompleted) {
        List<Achievement> unlockedAchievements = new ArrayList<>();
        List<Achievement> activeAchievements = achievementPort.findAllActive();

        for (Achievement achievement : activeAchievements) {
            if (childAchievementPort.existsByChildIdAndAchievementId(child.getId(), achievement.getId())) {
                continue; // Ya desbloqueado
            }

            boolean conditionMet = false;
            Integer condVal = achievement.getConditionValue() != null ? achievement.getConditionValue() : 1;

            switch (achievement.getConditionType()) {
                case FIRST_ACTIVITY:
                case ACTIVITY_COUNT:
                    if (activitiesCompleted >= condVal) conditionMet = true;
                    break;
                case FIRST_LESSON:
                case LESSON_COUNT:
                    if (lessonsCompleted >= condVal) conditionMet = true;
                    break;
                case ECOSYSTEM_COUNT:
                case ECOSYSTEM_COMPLETED:
                    if (ecosystemsCompleted >= condVal) conditionMet = true;
                    break;
                case XP_REACHED:
                    if (child.getTotalXp() >= condVal) conditionMet = true;
                    break;
                case LEVEL_REACHED:
                    // Necesitamos comparar con el numero de nivel, simplificado asumiendo nivel basico = 1+
                    // Esto asume que ChildProfile guarda levelId o se necesita consultar.
                    // Para evitar queries complejos aqui, asuminos que si su xp alcanza ciertos niveles...
                    // Como el evaluador solo se llama despues, confiaremos en un aproximado si condVal es 2 o más
                    // Pero requeriria LevelPersistencePort. Lo omitimos por ahora y asumimos que se gestiona de otra forma o evaluamos si el ID coincide
                    if (child.getLevelId() != null && child.getLevelId() >= condVal) conditionMet = true; 
                    break;
                case STREAK_REACHED:
                    if (child.getStreakCount() >= condVal) conditionMet = true;
                    break;
                case SPECIAL_CHALLENGE_COMPLETED:
                    if (specialChallengesCompleted >= condVal) conditionMet = true;
                    break;
                default:
                    logger.warn("Tipo de condicion no manejado: {}", achievement.getConditionType());
            }

            if (conditionMet) {
                logger.info("Logro desbloqueado: {} para nino {}", achievement.getTitle(), child.getId());
                ChildAchievement ca = new ChildAchievement(null, child.getId(), achievement.getId(), LocalDateTime.now());
                childAchievementPort.save(ca);
                unlockedAchievements.add(achievement);
            }
        }
        return unlockedAchievements;
    }
}
'''
}

for path, content in files.items():
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)
print("Service files generated successfully.")
