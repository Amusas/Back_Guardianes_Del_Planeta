package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.DomainException;
import com.guardianesdelplaneta.domain.model.*;
import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;
import com.guardianesdelplaneta.domain.model.enums.XPTransactionType;
import com.guardianesdelplaneta.domain.port.in.SubmitActivityAttemptUseCase;
import com.guardianesdelplaneta.domain.port.out.*;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptRequestDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ActivityAttemptResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityAttemptService implements SubmitActivityAttemptUseCase {
    private static final Logger logger = LoggerFactory.getLogger(ActivityAttemptService.class);

    private final ChildProfilePersistencePort childProfilePort;
    private final ActivityPersistencePort activityPort;
    private final ActivityAttemptPersistencePort attemptPort;
    private final XPTransactionPersistencePort xpTransactionPort;
    private final LevelPersistencePort levelPort;
    private final ProgressPersistencePort progressPort;
    private final StreakPersistencePort streakPort;
    private final RewardPersistencePort rewardPort;
    private final ChildRewardPersistencePort childRewardPort;
    private final LessonPersistencePort lessonPort;
    private final ActivityEvaluator evaluator;

    public ActivityAttemptService(
            ChildProfilePersistencePort childProfilePort,
            ActivityPersistencePort activityPort,
            ActivityAttemptPersistencePort attemptPort,
            XPTransactionPersistencePort xpTransactionPort,
            LevelPersistencePort levelPort,
            ProgressPersistencePort progressPort,
            StreakPersistencePort streakPort,
            RewardPersistencePort rewardPort,
            ChildRewardPersistencePort childRewardPort,
            LessonPersistencePort lessonPort,
            ActivityEvaluator evaluator) {
        this.childProfilePort = childProfilePort;
        this.activityPort = activityPort;
        this.attemptPort = attemptPort;
        this.xpTransactionPort = xpTransactionPort;
        this.levelPort = levelPort;
        this.progressPort = progressPort;
        this.streakPort = streakPort;
        this.rewardPort = rewardPort;
        this.childRewardPort = childRewardPort;
        this.lessonPort = lessonPort;
        this.evaluator = evaluator;
    }

    @Override
    @Transactional
    public ActivityAttemptResponseDto submitAttempt(Long userId, Long activityId, ActivityAttemptRequestDto request) {
        logger.info("Iniciando evaluación de intento para la actividad: {} por el usuario: {}", activityId, userId);

        ChildProfile child = childProfilePort.findByUserId(userId)
                .orElseThrow(() -> new DomainException("Perfil de niño no encontrado para el usuario: " + userId));

        Activity activity = activityPort.findById(activityId)
                .orElseThrow(() -> new DomainException("Actividad no encontrada: " + activityId));

        boolean isCorrect = evaluator.evaluate(activity, request.response());
        
        long attemptCount = attemptPort.countByChildIdAndActivityId(child.getId(), activityId);
        int attemptNumber = (int) attemptCount + 1;

        List<String> xpEvents = new ArrayList<>();
        int xpAwarded = 0;

        if (isCorrect) {
            // Actividad correcta (+10 XP)
            if (!xpTransactionPort.existsByChildIdAndTypeAndReferenceId(child.getId(), XPTransactionType.ACTIVITY, activityId)) {
                xpAwarded += 10;
                xpEvents.add("Correct: +10 XP");
                saveXPTransaction(child.getId(), XPTransactionType.ACTIVITY, activityId, 10, "Actividad completada correctamente");
            }

            // Sin errores (+20 XP)
            if (attemptNumber == 1) {
                if (!xpTransactionPort.existsByChildIdAndTypeAndReferenceId(child.getId(), XPTransactionType.BONUS, activityId)) {
                    xpAwarded += 20;
                    xpEvents.add("Perfect: +20 XP");
                    saveXPTransaction(child.getId(), XPTransactionType.BONUS, activityId, 20, "Completado al primer intento");
                }
            }
        }

        // Daily Streak (+25 XP)
        LocalDate today = LocalDate.now(ZoneId.of("America/Bogota"));
        Optional<Streak> todayStreak = streakPort.findByChildIdAndActivityDate(child.getId(), today);
        if (todayStreak.isEmpty()) {
            Streak streak = new Streak();
            streak.setChildId(child.getId());
            streak.setActivityDate(today);
            streak.setCreatedAt(LocalDateTime.now());
            // Determinar dias consecutivos
            Optional<Streak> yesterdayStreak = streakPort.findByChildIdAndActivityDate(child.getId(), today.minusDays(1));
            int consecutive = yesterdayStreak.map(s -> s.getConsecutiveDay() + 1).orElse(1);
            streak.setConsecutiveDay(consecutive);
            streakPort.save(streak);
            
            child.setStreakCount(consecutive);

            if (!xpTransactionPort.existsByChildIdAndTypeAndReferenceId(child.getId(), XPTransactionType.BONUS, 99999L + today.toEpochDay())) {
                xpAwarded += 25;
                xpEvents.add("Daily Streak: +25 XP");
                saveXPTransaction(child.getId(), XPTransactionType.BONUS, 99999L + today.toEpochDay(), 25, "Racha diaria");
            }
        }

        // Add XP to profile
        if (xpAwarded > 0) {
            child.setTotalXp(child.getTotalXp() + xpAwarded);
        }

        // Level Up Logic
        Long previousLevelId = child.getLevelId();
        boolean levelUp = false;
        
        // Asumiendo que podemos obtener el nivel siguiente
        int currentLvlNum = 1;
        if (previousLevelId != null) {
            Optional<Level> cur = levelPort.findById(previousLevelId);
            if (cur.isPresent()) currentLvlNum = cur.get().getLevelNumber();
        }
        
        Optional<Level> nextLvl = levelPort.findByLevelNumber(currentLvlNum + 1);
        while (nextLvl.isPresent() && child.getTotalXp() >= nextLvl.get().getRequiredXp()) {
            child.setLevelId(nextLvl.get().getId());
            levelUp = true;
            logger.info("El niño {} ha subido al nivel {}", child.getId(), nextLvl.get().getLevelNumber());
            
            // Rewards
            List<Reward> rewards = rewardPort.findByRequiredLevelIdLessThanEqual(child.getLevelId());
            for (Reward r : rewards) {
                if (!childRewardPort.existsByChildIdAndRewardId(child.getId(), r.getId())) {
                    ChildReward cr = new ChildReward();
                    cr.setChildId(child.getId());
                    cr.setRewardId(r.getId());
                    cr.setUnlockedAt(LocalDateTime.now());
                    childRewardPort.save(cr);
                }
            }
            
            currentLvlNum++;
            nextLvl = levelPort.findByLevelNumber(currentLvlNum + 1);
        }

        childProfilePort.save(child);

        // Record attempt
        ActivityAttempt attempt = new ActivityAttempt();
        attempt.setChildId(child.getId());
        attempt.setActivityId(activityId);
        attempt.setAttemptNumber(attemptNumber);
        attempt.setResponseJson(request.response().toString());
        attempt.setIsCorrect(isCorrect);
        attempt.setXpAwarded(xpAwarded);
        attempt.setAttemptedAt(LocalDateTime.now());
        attemptPort.save(attempt);

        return new ActivityAttemptResponseDto(
                isCorrect,
                attemptNumber,
                xpAwarded,
                child.getTotalXp(),
                child.getLevelId(),
                levelUp,
                previousLevelId,
                isCorrect,
                false,
                false,
                child.getStreakCount(),
                xpEvents
        );
    }

    private void saveXPTransaction(Long childId, XPTransactionType type, Long refId, int amount, String desc) {
        XPTransaction tx = new XPTransaction();
        tx.setChildId(childId);
        tx.setTransactionType(type);
        tx.setAmount(amount);
        tx.setReferenceId(refId);
        tx.setDescription(desc);
        tx.setCreatedAt(LocalDateTime.now());
        xpTransactionPort.save(tx);
    }
}
