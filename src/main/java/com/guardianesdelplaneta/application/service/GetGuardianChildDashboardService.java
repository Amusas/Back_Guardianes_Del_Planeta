package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.ChildProfileNotFoundException;
import com.guardianesdelplaneta.domain.exception.ForbiddenAccessException;
import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;
import com.guardianesdelplaneta.domain.port.in.GetGuardianChildDashboardUseCase;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.ChildAchievementSummaryDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.EcosystemProgressDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.GuardianChildDashboardDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.RecentActivityDto;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.XpHistoryItemDto;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityAttemptEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildAchievementEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ChildProfileEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.EcosystemEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LevelEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ProgressEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.XPTransactionEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.AchievementRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ActivityAttemptRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildAchievementRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ChildProfileRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.EcosystemRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.GuardianChildRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LessonRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LevelRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ProgressRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.XPTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetGuardianChildDashboardService implements GetGuardianChildDashboardUseCase {

    private static final Logger log = LoggerFactory.getLogger(GetGuardianChildDashboardService.class);

    private final GuardianChildRepository guardianChildRepository;
    private final ChildProfileRepository childProfileRepository;
    private final LevelRepository levelRepository;
    private final ProgressRepository progressRepository;
    private final LessonRepository lessonRepository;
    private final ActivityAttemptRepository activityAttemptRepository;
    private final XPTransactionRepository xpTransactionRepository;
    private final ChildAchievementRepository childAchievementRepository;
    private final AchievementRepository achievementRepository;
    private final EcosystemRepository ecosystemRepository;

    public GetGuardianChildDashboardService(
            GuardianChildRepository guardianChildRepository,
            ChildProfileRepository childProfileRepository,
            LevelRepository levelRepository,
            ProgressRepository progressRepository,
            LessonRepository lessonRepository,
            ActivityAttemptRepository activityAttemptRepository,
            XPTransactionRepository xpTransactionRepository,
            ChildAchievementRepository childAchievementRepository,
            AchievementRepository achievementRepository,
            EcosystemRepository ecosystemRepository) {
        this.guardianChildRepository = guardianChildRepository;
        this.childProfileRepository = childProfileRepository;
        this.levelRepository = levelRepository;
        this.progressRepository = progressRepository;
        this.lessonRepository = lessonRepository;
        this.activityAttemptRepository = activityAttemptRepository;
        this.xpTransactionRepository = xpTransactionRepository;
        this.childAchievementRepository = childAchievementRepository;
        this.achievementRepository = achievementRepository;
        this.ecosystemRepository = ecosystemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public GuardianChildDashboardDto getChildDashboard(Long guardianUserId, Long childId) {
        log.info("Consultando dashboard infantil para acudiente ID {} y niño ID {}", guardianUserId, childId);

        // 1. Autorización: verificar que el niño esté vinculado al acudiente
        if (!guardianChildRepository.existsByGuardianIdAndChildId(guardianUserId, childId)) {
            log.warn("Acceso denegado: el niño ID {} no está vinculado al acudiente ID {}", childId, guardianUserId);
            throw new ForbiddenAccessException("No tienes autorización para consultar la información de este perfil infantil");
        }

        // 2. Obtener el perfil infantil
        ChildProfileEntity child = childProfileRepository.findById(childId)
                .orElseThrow(() -> {
                    log.warn("Perfil infantil no encontrado para ID {}", childId);
                    return new ChildProfileNotFoundException("Perfil infantil no encontrado");
                });

        // 3. Nivel actual y cálculo hacia el siguiente nivel
        LevelEntity currentLevel = child.getLevel();
        int currentLevelNum = currentLevel != null ? currentLevel.getLevelNumber() : 1;
        String levelName = currentLevel != null ? currentLevel.getName() : "Semilla Guardiana";
        int currentLevelXp = currentLevel != null ? currentLevel.getRequiredXp() : 0;

        Optional<LevelEntity> nextLevelOpt = levelRepository.findByLevelNumber(currentLevelNum + 1);
        Integer nextLevelRequiredXp = nextLevelOpt.map(LevelEntity::getRequiredXp).orElse(null);
        String nextLevelName = nextLevelOpt.map(LevelEntity::getName).orElse(null);

        double levelProgress = 100.0;
        if (nextLevelRequiredXp != null) {
            int xpSpan = nextLevelRequiredXp - currentLevelXp;
            if (xpSpan > 0) {
                int xpInLevel = Math.max(0, child.getTotalXp() - currentLevelXp);
                levelProgress = Math.min(100.0, ((double) xpInLevel / xpSpan) * 100.0);
            }
        }

        // 4. Progreso de lecciones
        List<ProgressEntity> progresses = progressRepository.findByChildId(childId);
        int completedLessons = (int) progresses.stream()
                .filter(p -> p.getStatus() == ProgressStatus.COMPLETED)
                .count();
        int totalLessons = (int) lessonRepository.count();

        // 5. Intentos de actividad y cálculo de precisión
        List<ActivityAttemptEntity> attempts = activityAttemptRepository.findByChildId(childId);
        int totalAttempts = attempts.size();
        int correctAttempts = (int) attempts.stream()
                .filter(a -> Boolean.TRUE.equals(a.getIsCorrect()))
                .count();
        double accuracy = totalAttempts > 0 ? ((double) correctAttempts / totalAttempts) * 100.0 : 0.0;

        // 6. Logros
        List<ChildAchievementEntity> childAchievements = childAchievementRepository.findByChildId(childId);
        List<ChildAchievementSummaryDto> achievementDtos = childAchievements.stream()
                .map(ca -> new ChildAchievementSummaryDto(
                        ca.getAchievement().getId(),
                        ca.getAchievement().getTitle(),
                        ca.getAchievement().getDescription(),
                        ca.getAchievement().getVisualKey(),
                        ca.getUnlockedAt()
                ))
                .toList();
        int totalAchievements = (int) achievementRepository.count();

        // 7. Progreso por ecosistema
        List<EcosystemEntity> ecosystems = ecosystemRepository.findByIsActiveTrue();
        List<EcosystemProgressDto> ecosystemProgressDtos = ecosystems.stream().map(eco -> {
            List<LessonEntity> lessons = lessonRepository.findByEcosystemIdOrderByOrderIndexAsc(eco.getId());
            int ecoTotal = lessons.size();
            int ecoCompleted = (int) progresses.stream()
                    .filter(p -> p.getStatus() == ProgressStatus.COMPLETED &&
                            lessons.stream().anyMatch(l -> l.getId().equals(p.getLesson().getId())))
                    .count();
            double ecoPercentage = ecoTotal > 0 ? ((double) ecoCompleted / ecoTotal) * 100.0 : 0.0;
            return new EcosystemProgressDto(eco.getId(), eco.getCode(), eco.getName(), ecoCompleted, ecoTotal, ecoPercentage);
        }).toList();

        // 8. Historial de transacciones XP
        List<XPTransactionEntity> xpTxs = xpTransactionRepository.findByChildIdOrderByCreatedAtDesc(childId);
        List<XpHistoryItemDto> xpHistoryDtos = xpTxs.stream()
                .map(tx -> new XpHistoryItemDto(
                        tx.getId(),
                        tx.getAmount(),
                        tx.getType() != null ? tx.getType().name() : "ACTIVITY",
                        tx.getDescription(),
                        tx.getCreatedAt()
                ))
                .toList();

        // 9. Línea de tiempo reciente consolidada
        List<RecentActivityDto> recentActivities = new ArrayList<>();
        for (ActivityAttemptEntity att : attempts) {
            String title = (att.getActivity() != null && att.getActivity().getTitle() != null)
                    ? att.getActivity().getTitle()
                    : "Actividad";
            recentActivities.add(new RecentActivityDto(
                    title,
                    Boolean.TRUE.equals(att.getIsCorrect()) ? "Respuesta correcta" : "Intento de práctica",
                    "ACTIVITY",
                    att.getXpAwarded() != null ? att.getXpAwarded() : 0,
                    att.getAttemptedAt(),
                    Boolean.TRUE.equals(att.getIsCorrect())
            ));
        }
        for (ProgressEntity p : progresses) {
            if (p.getStatus() == ProgressStatus.COMPLETED && p.getCompletedAt() != null) {
                String title = (p.getLesson() != null && p.getLesson().getTitle() != null)
                        ? p.getLesson().getTitle()
                        : "Lección completada";
                recentActivities.add(new RecentActivityDto(
                        title,
                        "Lección completada al 100%",
                        "LESSON",
                        (p.getLesson() != null && p.getLesson().getXpReward() != null) ? p.getLesson().getXpReward() : 0,
                        p.getCompletedAt(),
                        true
                ));
            }
        }
        for (ChildAchievementEntity ca : childAchievements) {
            recentActivities.add(new RecentActivityDto(
                    ca.getAchievement().getTitle(),
                    "Logro desbloqueado: " + ca.getAchievement().getDescription(),
                    "ACHIEVEMENT",
                    0,
                    ca.getUnlockedAt(),
                    true
            ));
        }

        // Ordenar descendentemente por fecha
        recentActivities.sort((a, b) -> {
            if (a.timestamp() == null && b.timestamp() == null) return 0;
            if (a.timestamp() == null) return 1;
            if (b.timestamp() == null) return -1;
            return b.timestamp().compareTo(a.timestamp());
        });

        if (recentActivities.size() > 10) {
            recentActivities = recentActivities.subList(0, 10);
        }

        // Calcular edad
        Integer age = child.getDateOfBirth() != null ? Period.between(child.getDateOfBirth(), LocalDate.now()).getYears() : null;
        String username = child.getUser() != null ? child.getUser().getUsername() : "";

        log.info("Dashboard calculado para niño ID {} con éxito. Lecciones: {}/{}, XP: {}, Nivel: {}",
                childId, completedLessons, totalLessons, child.getTotalXp(), levelName);

        return new GuardianChildDashboardDto(
                child.getId(),
                child.getName(),
                username,
                child.getDateOfBirth(),
                age,
                child.getTotalXp(),
                currentLevelNum,
                levelName,
                currentLevelXp,
                nextLevelRequiredXp,
                nextLevelName,
                levelProgress,
                completedLessons,
                totalLessons,
                totalAttempts,
                correctAttempts,
                accuracy,
                child.getStreakCount() != null ? child.getStreakCount() : 0,
                childAchievements.size(),
                totalAchievements,
                achievementDtos,
                ecosystemProgressDtos,
                xpHistoryDtos,
                recentActivities
        );
    }
}
