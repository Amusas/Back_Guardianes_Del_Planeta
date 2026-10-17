package com.guardianesdelplaneta.application.service;

import com.guardianesdelplaneta.domain.exception.ChildProfileNotFoundException;
import com.guardianesdelplaneta.domain.exception.ForbiddenAccessException;
import com.guardianesdelplaneta.domain.model.enums.ProgressStatus;
import com.guardianesdelplaneta.domain.model.enums.XPTransactionType;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.GuardianChildDashboardDto;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.*;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetGuardianChildDashboardServiceTest {

    @Mock
    private GuardianChildRepository guardianChildRepository;

    @Mock
    private ChildProfileRepository childProfileRepository;

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private ProgressRepository progressRepository;

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private ActivityAttemptRepository activityAttemptRepository;

    @Mock
    private XPTransactionRepository xpTransactionRepository;

    @Mock
    private ChildAchievementRepository childAchievementRepository;

    @Mock
    private AchievementRepository achievementRepository;

    @Mock
    private EcosystemRepository ecosystemRepository;

    private GetGuardianChildDashboardService service;

    @BeforeEach
    void setUp() {
        service = new GetGuardianChildDashboardService(
                guardianChildRepository,
                childProfileRepository,
                levelRepository,
                progressRepository,
                lessonRepository,
                activityAttemptRepository,
                xpTransactionRepository,
                childAchievementRepository,
                achievementRepository,
                ecosystemRepository
        );
    }

    @Test
    void getChildDashboard_Success_WithEcosystemsXpHistoryAndMoreThan10RecentActivities() {
        Long guardianId = 1L;
        Long childId = 10L;

        when(guardianChildRepository.existsByGuardianIdAndChildId(guardianId, childId)).thenReturn(true);

        UserEntity user = new UserEntity();
        user.setId(2L);
        user.setUsername("pedrito123");

        LevelEntity level1 = new LevelEntity();
        level1.setId(1L);
        level1.setLevelNumber(1);
        level1.setName("Semilla Guardiana");
        level1.setRequiredXp(0);

        LevelEntity level2 = new LevelEntity();
        level2.setId(2L);
        level2.setLevelNumber(2);
        level2.setName("Explorador del Agua");
        level2.setRequiredXp(500);
        when(levelRepository.findByLevelNumber(2)).thenReturn(Optional.of(level2));

        ChildProfileEntity child = new ChildProfileEntity();
        child.setId(childId);
        child.setUser(user);
        child.setName("Pedrito");
        child.setDateOfBirth(LocalDate.now().minusYears(8));
        child.setTotalXp(250);
        child.setStreakCount(3);
        child.setLevel(level1);
        when(childProfileRepository.findById(childId)).thenReturn(Optional.of(child));

        LessonEntity lesson1 = new LessonEntity();
        lesson1.setId(100L);
        lesson1.setTitle("Lección 1");
        lesson1.setXpReward(25);

        ProgressEntity progress1 = new ProgressEntity();
        progress1.setChild(child);
        progress1.setLesson(lesson1);
        progress1.setStatus(ProgressStatus.COMPLETED);
        progress1.setCompletedAt(LocalDateTime.now().minusMinutes(30));

        when(progressRepository.findByChildId(childId)).thenReturn(List.of(progress1));
        when(lessonRepository.count()).thenReturn(10L);

        List<ActivityAttemptEntity> attempts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ActivityEntity act = new ActivityEntity();
            act.setId((long) i);
            act.setTitle("Act " + i);
            ActivityAttemptEntity att = new ActivityAttemptEntity();
            att.setId((long) i);
            att.setActivity(i % 2 == 0 ? act : null);
            att.setIsCorrect(i % 2 == 0);
            att.setXpAwarded(i % 2 == 0 ? 10 : null);
            att.setAttemptedAt(i == 9 ? null : LocalDateTime.now().minusHours(i));
            attempts.add(att);
        }
        when(activityAttemptRepository.findByChildId(childId)).thenReturn(attempts);

        AchievementEntity ach = new AchievementEntity();
        ach.setId(5L);
        ach.setTitle("Primer Paso");
        ach.setDescription("Inició su aventura");
        ach.setVisualKey("FIRST_STEP");

        ChildAchievementEntity ca = new ChildAchievementEntity();
        ca.setId(1L);
        ca.setChild(child);
        ca.setAchievement(ach);
        ca.setUnlockedAt(LocalDateTime.now().minusDays(1));
        when(childAchievementRepository.findByChildId(childId)).thenReturn(List.of(ca));
        when(achievementRepository.count()).thenReturn(5L);

        EcosystemEntity eco = new EcosystemEntity();
        eco.setId(1L);
        eco.setCode("BOSQUE_ANDINO");
        eco.setName("Bosque Andino");
        when(ecosystemRepository.findByIsActiveTrue()).thenReturn(List.of(eco));
        when(lessonRepository.findByEcosystemIdOrderByOrderIndexAsc(1L)).thenReturn(List.of(lesson1));

        XPTransactionEntity tx = new XPTransactionEntity();
        tx.setId(1L);
        tx.setAmount(25);
        tx.setType(XPTransactionType.LESSON);
        tx.setDescription("Completada Lección 1");
        tx.setCreatedAt(LocalDateTime.now());
        when(xpTransactionRepository.findByChildIdOrderByCreatedAtDesc(childId)).thenReturn(List.of(tx));

        GuardianChildDashboardDto dashboard = service.getChildDashboard(guardianId, childId);

        assertNotNull(dashboard);
        assertEquals("Pedrito", dashboard.name());
        assertEquals("pedrito123", dashboard.username());
        assertEquals(8, dashboard.age());
        assertEquals(250, dashboard.totalXp());
        assertEquals(1, dashboard.ecosystemProgress().size());
        assertEquals(100.0, dashboard.ecosystemProgress().get(0).progressPercentage());
        assertEquals(1, dashboard.xpHistory().size());
        assertEquals(10, dashboard.recentActivities().size());
    }

    @Test
    void getChildDashboard_ThrowsWhenNotAssociated() {
        when(guardianChildRepository.existsByGuardianIdAndChildId(1L, 99L)).thenReturn(false);

        assertThrows(ForbiddenAccessException.class, () ->
                service.getChildDashboard(1L, 99L)
        );
    }

    @Test
    void getChildDashboard_ThrowsWhenChildNotFound() {
        when(guardianChildRepository.existsByGuardianIdAndChildId(1L, 99L)).thenReturn(true);
        when(childProfileRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ChildProfileNotFoundException.class, () ->
                service.getChildDashboard(1L, 99L)
        );
    }
}
