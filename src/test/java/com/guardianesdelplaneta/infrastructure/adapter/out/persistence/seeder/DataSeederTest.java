package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.seeder;

import com.guardianesdelplaneta.domain.model.enums.ActivityType;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.AchievementEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.ActivityEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.EcosystemEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LessonEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.LevelEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.RewardEntity;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.AchievementRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.ActivityRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.EcosystemRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LessonRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.LevelRepository;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.RewardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataSeederTest {

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private EcosystemRepository ecosystemRepository;

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private AchievementRepository achievementRepository;

    @Mock
    private RewardRepository rewardRepository;

    @InjectMocks
    private DataSeeder dataSeeder;

    private List<LessonEntity> createLessons1To9(EcosystemEntity eco) {
        List<LessonEntity> lessons = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            LessonEntity lesson = new LessonEntity();
            lesson.setId((long) i);
            lesson.setEcosystem(eco);
            lesson.setTitle("Lección " + i);
            lesson.setOrderIndex(i);
            lesson.setXpReward(20);
            lesson.setIsActive(true);
            lessons.add(lesson);
        }
        return lessons;
    }

    @Test
    @DisplayName("run debe crear niveles, ecosistemas, lecciones, actividades (7 ecosistemas), logros y recompensas cuando la BD está vacía")
    void run_WhenDatabaseEmpty_SeedsEverything() {
        when(levelRepository.findByLevelNumber(anyInt())).thenReturn(Optional.empty());
        when(levelRepository.save(any(LevelEntity.class))).thenAnswer(inv -> {
            LevelEntity l = inv.getArgument(0);
            if (l.getId() == null) l.setId(1L);
            return l;
        });

        EcosystemEntity sampleEco = new EcosystemEntity();
        sampleEco.setId(1L);
        sampleEco.setCode("BOSQUE_ANDINO");

        Map<String, Integer> ecoCallCounts = new HashMap<>();
        when(ecosystemRepository.findByCode(anyString())).thenAnswer(inv -> {
            String code = inv.getArgument(0);
            int count = ecoCallCounts.getOrDefault(code, 0) + 1;
            ecoCallCounts.put(code, count);
            if (count == 1) {
                return Optional.empty();
            }
            EcosystemEntity e = new EcosystemEntity();
            e.setId(1L);
            e.setCode(code);
            return Optional.of(e);
        });

        when(ecosystemRepository.save(any(EcosystemEntity.class))).thenAnswer(inv -> {
            EcosystemEntity e = inv.getArgument(0);
            if (e.getId() == null) e.setId(1L);
            return e;
        });

        final int[] lessonQueryCount = {0};
        when(lessonRepository.findByEcosystemIdOrderByOrderIndexAsc(any())).thenAnswer(inv -> {
            lessonQueryCount[0]++;
            // Las primeras 7 llamadas son durante seedEcosystemsAndLessons (vacío -> crea nuevas)
            if (lessonQueryCount[0] <= 7) {
                return List.of();
            }
            // Las siguientes 7 llamadas son durante seedEcosystemActivities (retorna lecciones 1..9)
            return createLessons1To9(sampleEco);
        });

        when(lessonRepository.save(any(LessonEntity.class))).thenAnswer(inv -> inv.getArgument(0));
        when(activityRepository.findByLessonIdOrderByOrderIndexAsc(any())).thenReturn(List.of());
        when(achievementRepository.findByCode(anyString())).thenReturn(Optional.empty());
        when(rewardRepository.findByVisualKey(anyString())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> dataSeeder.run());

        verify(levelRepository, atLeastOnce()).save(any(LevelEntity.class));
        verify(ecosystemRepository, atLeastOnce()).save(any(EcosystemEntity.class));
        verify(activityRepository, atLeastOnce()).saveAll(any());
        verify(achievementRepository, atLeastOnce()).save(any(AchievementEntity.class));
        verify(rewardRepository, atLeastOnce()).save(any(RewardEntity.class));
    }

    @Test
    @DisplayName("run debe actualizar registros existentes in-place y manejar excepciones de limpieza (Camino Alterno y Triste)")
    void run_WhenDataAlreadyExists_UpdatesInPlaceAndHandlesExceptions() {
        LevelEntity existingLevel = new LevelEntity();
        existingLevel.setId(1L);
        existingLevel.setLevelNumber(1);
        when(levelRepository.findByLevelNumber(anyInt())).thenReturn(Optional.of(existingLevel));
        when(levelRepository.save(any(LevelEntity.class))).thenReturn(existingLevel);

        EcosystemEntity existingEco = new EcosystemEntity();
        existingEco.setId(1L);
        existingEco.setCode("BOSQUE_ANDINO");
        when(ecosystemRepository.findByCode(anyString())).thenReturn(Optional.of(existingEco));
        when(ecosystemRepository.save(any(EcosystemEntity.class))).thenReturn(existingEco);

        List<LessonEntity> existingLessons = createLessons1To9(existingEco);
        when(lessonRepository.findByEcosystemIdOrderByOrderIndexAsc(any())).thenReturn(existingLessons);
        when(lessonRepository.save(any(LessonEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        ActivityEntity existingAct1 = new ActivityEntity();
        existingAct1.setId(100L);
        existingAct1.setOrderIndex(1);
        existingAct1.setType(ActivityType.MULTIPLE_CHOICE);

        ActivityEntity extraAct6 = new ActivityEntity();
        extraAct6.setId(106L);
        extraAct6.setOrderIndex(6);
        extraAct6.setType(ActivityType.TRUE_FALSE);

        when(activityRepository.findByLessonIdOrderByOrderIndexAsc(any()))
                .thenReturn(List.of(existingAct1, extraAct6));

        doThrow(new RuntimeException("Error simulado purgando IDENTIFICATION"))
                .when(activityRepository).deleteIdentificationActivities();
        doThrow(new RuntimeException("Error simulado eliminando extras"))
                .when(activityRepository).deleteAll(any());

        AchievementEntity existingAch = new AchievementEntity();
        existingAch.setId(1L);
        when(achievementRepository.findByCode(anyString())).thenReturn(Optional.of(existingAch));

        RewardEntity existingReward = new RewardEntity();
        existingReward.setId(1L);
        when(rewardRepository.findByVisualKey(anyString())).thenReturn(Optional.of(existingReward));

        assertDoesNotThrow(() -> dataSeeder.run());

        verify(activityRepository, atLeastOnce()).save(any(ActivityEntity.class));
        verify(achievementRepository, atLeastOnce()).save(existingAch);
        verify(rewardRepository, atLeastOnce()).save(existingReward);
    }
}
