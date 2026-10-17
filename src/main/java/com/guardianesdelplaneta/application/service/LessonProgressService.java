package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.ChildProfileNotFoundException;
import com.guardianesdelplaneta.domain.exception.DomainException;
import com.guardianesdelplaneta.domain.model.ChildProfile;
import com.guardianesdelplaneta.domain.model.Lesson;
import com.guardianesdelplaneta.domain.model.Progress;
import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;
import com.guardianesdelplaneta.domain.port.in.CompleteLessonUseCase;
import com.guardianesdelplaneta.domain.port.in.GetChildProgressUseCase;
import com.guardianesdelplaneta.domain.port.out.ChildProfilePersistencePort;
import com.guardianesdelplaneta.domain.port.out.LessonPersistencePort;
import com.guardianesdelplaneta.domain.port.out.ProgressPersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LessonProgressService implements CompleteLessonUseCase, GetChildProgressUseCase {

    private static final Logger log = LoggerFactory.getLogger(LessonProgressService.class);

    private final ChildProfilePersistencePort childProfilePort;
    private final LessonPersistencePort lessonPort;
    private final ProgressPersistencePort progressPort;
    private final AchievementEvaluator achievementEvaluator;

    public LessonProgressService(
            ChildProfilePersistencePort childProfilePort,
            LessonPersistencePort lessonPort,
            ProgressPersistencePort progressPort,
            AchievementEvaluator achievementEvaluator) {
        this.childProfilePort = childProfilePort;
        this.lessonPort = lessonPort;
        this.progressPort = progressPort;
        this.achievementEvaluator = achievementEvaluator;
    }

    @Override
    @Transactional
    public Progress completeLesson(Long userId, Long lessonId) {
        log.info("Completando lección ID {} para el usuario {}", lessonId, userId);

        ChildProfile child = childProfilePort.findByUserId(userId)
                .orElseThrow(() -> new ChildProfileNotFoundException("Perfil de niño no encontrado para el usuario: " + userId));

        Lesson lesson = lessonPort.findById(lessonId)
                .orElseThrow(() -> new DomainException("Lección no encontrada con ID: " + lessonId));

        Progress progress = progressPort.findByChildIdAndLessonId(child.getId(), lessonId)
                .orElseGet(() -> {
                    Progress p = new Progress();
                    p.setChildId(child.getId());
                    p.setLessonId(lessonId);
                    return p;
                });

        progress.setStatus(ProgressStatus.COMPLETED);
        progress.setProgressPercentage(100);
        if (progress.getCompletedAt() == null) {
            progress.setCompletedAt(LocalDateTime.now());
        }
        progress.setUpdatedAt(LocalDateTime.now());

        Progress saved = progressPort.save(progress);
        log.info("Progreso guardado exitosamente para niño ID {} y lección ID {}", child.getId(), lessonId);

        // Evaluar logros de lección
        try {
            List<Progress> childProgresses = progressPort.findByChildId(child.getId());
            long completedLessonsCount = childProgresses.stream()
                    .filter(p -> p.getStatus() == ProgressStatus.COMPLETED)
                    .count();

            long completedEcosystems = 0;
            if (lesson.getEcosystemId() != null) {
                List<Lesson> ecoLessons = lessonPort.findActiveLessonsByEcosystemId(lesson.getEcosystemId());
                if (!ecoLessons.isEmpty() && ecoLessons.stream().allMatch(l ->
                        childProgresses.stream().anyMatch(p -> p.getLessonId().equals(l.getId()) && p.getStatus() == ProgressStatus.COMPLETED))) {
                    completedEcosystems = 1;
                }
            }

            achievementEvaluator.evaluateAchievements(child, 0, (int) completedLessonsCount, (int) completedEcosystems, 0);
        } catch (Exception e) {
            log.warn("Error evaluando logros tras completar lección {}: {}", lessonId, e.getMessage());
        }

        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progress> getProgressByUserId(Long userId) {
        ChildProfile child = childProfilePort.findByUserId(userId)
                .orElseThrow(() -> new ChildProfileNotFoundException("Perfil de niño no encontrado para el usuario: " + userId));
        return progressPort.findByChildId(child.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> getCompletedLessonIdsByUserId(Long userId) {
        return getProgressByUserId(userId).stream()
                .filter(p -> p.getStatus() == ProgressStatus.COMPLETED)
                .map(Progress::getLessonId)
                .toList();
    }
}
