package com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper;

import com.guardianesdelplaneta.domain.model.*;
import com.guardianesdelplaneta.domain.model.enums.*;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AllPersistenceMappersAndEntitiesTest {

    private final AchievementPersistenceMapperImpl achievementMapper = new AchievementPersistenceMapperImpl();
    private final ActivityAttemptPersistenceMapperImpl attemptMapper = new ActivityAttemptPersistenceMapperImpl();
    private final ActivityPersistenceMapperImpl activityMapper = new ActivityPersistenceMapperImpl();
    private final ChildAchievementPersistenceMapperImpl childAchievementMapper = new ChildAchievementPersistenceMapperImpl();
    private final ChildProfilePersistenceMapperImpl childProfileMapper = new ChildProfilePersistenceMapperImpl();
    private final ChildRewardPersistenceMapperImpl childRewardMapper = new ChildRewardPersistenceMapperImpl();
    private final EcosystemPersistenceMapperImpl ecosystemMapper = new EcosystemPersistenceMapperImpl();
    private final GuardianChildPersistenceMapperImpl guardianChildMapper = new GuardianChildPersistenceMapperImpl();
    private final LessonPersistenceMapperImpl lessonMapper = new LessonPersistenceMapperImpl();
    private final LevelPersistenceMapperImpl levelMapper = new LevelPersistenceMapperImpl();
    private final ProgressPersistenceMapperImpl progressMapper = new ProgressPersistenceMapperImpl();
    private final RewardPersistenceMapperImpl rewardMapper = new RewardPersistenceMapperImpl();
    private final StreakPersistenceMapperImpl streakMapper = new StreakPersistenceMapperImpl();
    private final UserPersistenceMapperImpl userMapper = new UserPersistenceMapperImpl();
    private final XPTransactionPersistenceMapperImpl xpMapper = new XPTransactionPersistenceMapperImpl();

    @Test
    @DisplayName("Todos los PersistenceMappers deben mapear correctamente de dominio a entidad y viceversa (Camino Feliz)")
    void testAllMappers_HappyPath() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();

        Achievement ach = new Achievement(1L, "CODE1", "Title", "Desc", AchievementConditionType.FIRST_LESSON, 1, "KEY", true);
        AchievementEntity achEntity = achievementMapper.toEntity(ach);
        assertNotNull(achEntity);
        Achievement achBack = achievementMapper.toDomain(achEntity);
        assertEquals("CODE1", achBack.getCode());

        ActivityAttempt attempt = new ActivityAttempt(1L, 2L, 3L, 1, "{}", true, 10, now);
        ActivityAttemptEntity attemptEntity = attemptMapper.toEntity(attempt);
        assertNotNull(attemptEntity);
        ActivityAttempt attemptBack = attemptMapper.toDomain(attemptEntity);
        assertEquals(2L, attemptBack.getChildId());
        assertEquals(3L, attemptBack.getActivityId());

        Activity activity = new Activity(1L, 5L, ActivityType.MULTIPLE_CHOICE, "Title", "{}", 15, 1);
        ActivityEntity activityEntity = activityMapper.toEntity(activity);
        assertNotNull(activityEntity);
        Activity activityBack = activityMapper.toDomain(activityEntity);
        assertEquals(5L, activityBack.getLessonId());

        ChildAchievement ca = new ChildAchievement(1L, 2L, 3L, now);
        ChildAchievementEntity caEntity = childAchievementMapper.toEntity(ca);
        assertNotNull(caEntity);
        ChildAchievement caBack = childAchievementMapper.toDomain(caEntity);
        assertEquals(2L, caBack.getChildId());
        assertEquals(3L, caBack.getAchievementId());

        ChildProfile cp = new ChildProfile(1L, 2L, "Mateo", today.minusYears(8), 100, 1L, 3, now, now);
        ChildProfileEntity cpEntity = childProfileMapper.toEntity(cp);
        assertNotNull(cpEntity);
        ChildProfile cpBack = childProfileMapper.toDomain(cpEntity);
        assertEquals("Mateo", cpBack.getName());
        assertEquals(2L, cpBack.getUserId());

        ChildReward cr = new ChildReward(1L, 2L, 3L, now, true);
        ChildRewardEntity crEntity = childRewardMapper.toEntity(cr);
        assertNotNull(crEntity);
        ChildReward crBack = childRewardMapper.toDomain(crEntity);
        assertEquals(2L, crBack.getChildId());
        assertEquals(3L, crBack.getRewardId());

        Ecosystem eco = new Ecosystem(1L, "PARAMO", "Páramo", "Desc", 2L, "OSO", true);
        EcosystemEntity ecoEntity = ecosystemMapper.toEntity(eco);
        assertNotNull(ecoEntity);
        Ecosystem ecoBack = ecosystemMapper.toDomain(ecoEntity);
        assertEquals("PARAMO", ecoBack.getCode());
        assertEquals(2L, ecoBack.getRequiredLevelId());

        GuardianChild gc = new GuardianChild(1L, 2L, 3L, RelationshipType.MOTHER, true, now);
        GuardianChildEntity gcEntity = guardianChildMapper.toEntity(gc);
        assertNotNull(gcEntity);
        GuardianChild gcBack = guardianChildMapper.toDomain(gcEntity);
        assertEquals(2L, gcBack.getGuardianId());
        assertEquals(3L, gcBack.getChildId());

        Lesson lesson = new Lesson(1L, 2L, "Lesson 1", "Content", 1, 20, true);
        LessonEntity lessonEntity = lessonMapper.toEntity(lesson);
        assertNotNull(lessonEntity);
        Lesson lessonBack = lessonMapper.toDomain(lessonEntity);
        assertEquals(2L, lessonBack.getEcosystemId());

        Level level = new Level(1L, 1, "Semilla", 0, "Desc", "KEY");
        LevelEntity levelEntity = levelMapper.toEntity(level);
        assertNotNull(levelEntity);
        Level levelBack = levelMapper.toDomain(levelEntity);
        assertEquals(1, levelBack.getLevelNumber());

        Progress progress = new Progress(1L, 2L, 3L, ProgressStatus.COMPLETED, 100, now, now);
        ProgressEntity progressEntity = progressMapper.toEntity(progress);
        assertNotNull(progressEntity);
        Progress progressBack = progressMapper.toDomain(progressEntity);
        assertEquals(2L, progressBack.getChildId());
        assertEquals(3L, progressBack.getLessonId());

        Reward reward = new Reward(1L, "Gorra", "Desc", RewardType.ACCESSORY, "KEY", 1L, true);
        RewardEntity rewardEntity = rewardMapper.toEntity(reward);
        assertNotNull(rewardEntity);
        Reward rewardBack = rewardMapper.toDomain(rewardEntity);
        assertEquals(1L, rewardBack.getRequiredLevelId());

        Streak streak = new Streak(1L, 2L, today, 5, now);
        StreakEntity streakEntity = streakMapper.toEntity(streak);
        assertNotNull(streakEntity);
        Streak streakBack = streakMapper.toDomain(streakEntity);
        assertEquals(2L, streakBack.getChildId());

        User user = new User(1L, "user1", "hash", UserRole.GUARDIAN, UserStatus.ACTIVE, now, now);
        UserEntity userEntity = userMapper.toEntity(user);
        assertNotNull(userEntity);
        User userBack = userMapper.toDomain(userEntity);
        assertEquals("user1", userBack.getUsername());

        XPTransaction xp = new XPTransaction(1L, 2L, 50, XPTransactionType.ACTIVITY, 10L, "Desc", now);
        XPTransactionEntity xpEntity = xpMapper.toEntity(xp);
        assertNotNull(xpEntity);
        XPTransaction xpBack = xpMapper.toDomain(xpEntity);
        assertEquals(2L, xpBack.getChildId());
    }

    @Test
    @DisplayName("Todos los PersistenceMappers deben retornar null cuando reciben null y manejar relaciones con ID nulo (Camino Triste)")
    void testAllMappers_NullAndNestedNulls() throws Exception {
        assertNull(achievementMapper.toDomain(null));
        assertNull(achievementMapper.toEntity(null));

        assertNull(attemptMapper.toDomain(null));
        assertNull(attemptMapper.toEntity(null));
        assertNotNull(attemptMapper.toDomain(new ActivityAttemptEntity()));
        ActivityAttemptEntity attWithNullIds = new ActivityAttemptEntity();
        attWithNullIds.setChild(new ChildProfileEntity());
        attWithNullIds.setActivity(new ActivityEntity());
        assertNull(attemptMapper.toDomain(attWithNullIds).getChildId());
        assertNull(attemptMapper.activityAttemptToChildProfileEntity(null));
        assertNull(attemptMapper.activityAttemptToActivityEntity(null));

        assertNull(activityMapper.toDomain(null));
        assertNull(activityMapper.toEntity(null));
        assertNotNull(activityMapper.toDomain(new ActivityEntity()));
        ActivityEntity actWithNullId = new ActivityEntity();
        actWithNullId.setLesson(new LessonEntity());
        assertNull(activityMapper.toDomain(actWithNullId).getLessonId());
        assertNull(activityMapper.activityToLessonEntity(null));

        assertNull(childAchievementMapper.toDomain(null));
        assertNull(childAchievementMapper.toEntity(null));
        assertNotNull(childAchievementMapper.toDomain(new ChildAchievementEntity()));
        ChildAchievementEntity caWithNullIds = new ChildAchievementEntity();
        caWithNullIds.setChild(new ChildProfileEntity());
        caWithNullIds.setAchievement(new AchievementEntity());
        assertNull(childAchievementMapper.toDomain(caWithNullIds).getChildId());
        assertNull(childAchievementMapper.childAchievementToChildProfileEntity(null));
        assertNull(childAchievementMapper.childAchievementToAchievementEntity(null));

        assertNull(childProfileMapper.toDomain(null));
        assertNull(childProfileMapper.toEntity(null));
        assertNotNull(childProfileMapper.toDomain(new ChildProfileEntity()));
        ChildProfileEntity cpWithNullIds = new ChildProfileEntity();
        cpWithNullIds.setUser(new UserEntity());
        cpWithNullIds.setLevel(new LevelEntity());
        assertNull(childProfileMapper.toDomain(cpWithNullIds).getUserId());
        assertNull(childProfileMapper.childProfileToUserEntity(null));
        assertNull(childProfileMapper.childProfileToLevelEntity(null));

        assertNull(childRewardMapper.toDomain(null));
        assertNull(childRewardMapper.toEntity(null));
        assertNotNull(childRewardMapper.toDomain(new ChildRewardEntity()));
        ChildRewardEntity crWithNullIds = new ChildRewardEntity();
        crWithNullIds.setChild(new ChildProfileEntity());
        crWithNullIds.setReward(new RewardEntity());
        assertNull(childRewardMapper.toDomain(crWithNullIds).getChildId());
        assertNull(childRewardMapper.childRewardToChildProfileEntity(null));
        assertNull(childRewardMapper.childRewardToRewardEntity(null));

        assertNull(ecosystemMapper.toDomain(null));
        assertNull(ecosystemMapper.toEntity(null));
        assertNotNull(ecosystemMapper.toDomain(new EcosystemEntity()));
        EcosystemEntity ecoWithNullId = new EcosystemEntity();
        ecoWithNullId.setRequiredLevel(new LevelEntity());
        assertNull(ecosystemMapper.toDomain(ecoWithNullId).getRequiredLevelId());
        assertNull(ecosystemMapper.ecosystemToLevelEntity(null));

        assertNull(guardianChildMapper.toDomain(null));
        assertNull(guardianChildMapper.toEntity(null));
        assertNotNull(guardianChildMapper.toDomain(new GuardianChildEntity()));
        GuardianChildEntity gcWithNullIds = new GuardianChildEntity();
        gcWithNullIds.setGuardian(new UserEntity());
        gcWithNullIds.setChild(new ChildProfileEntity());
        assertNull(guardianChildMapper.toDomain(gcWithNullIds).getGuardianId());
        assertNull(guardianChildMapper.guardianChildToUserEntity(null));
        assertNull(guardianChildMapper.guardianChildToChildProfileEntity(null));

        assertNull(lessonMapper.toDomain(null));
        assertNull(lessonMapper.toEntity(null));
        assertNotNull(lessonMapper.toDomain(new LessonEntity()));
        LessonEntity lessonWithNullId = new LessonEntity();
        lessonWithNullId.setEcosystem(new EcosystemEntity());
        assertNull(lessonMapper.toDomain(lessonWithNullId).getEcosystemId());
        assertNull(lessonMapper.lessonToEcosystemEntity(null));

        assertNull(levelMapper.toDomain(null));
        assertNull(levelMapper.toEntity(null));

        assertNull(progressMapper.toDomain(null));
        assertNull(progressMapper.toEntity(null));
        assertNotNull(progressMapper.toDomain(new ProgressEntity()));
        ProgressEntity progWithNullIds = new ProgressEntity();
        progWithNullIds.setChild(new ChildProfileEntity());
        progWithNullIds.setLesson(new LessonEntity());
        assertNull(progressMapper.toDomain(progWithNullIds).getChildId());
        assertNull(progressMapper.progressToChildProfileEntity(null));
        assertNull(progressMapper.progressToLessonEntity(null));

        assertNull(rewardMapper.toDomain(null));
        assertNull(rewardMapper.toEntity(null));
        assertNotNull(rewardMapper.toDomain(new RewardEntity()));
        RewardEntity rewWithNullId = new RewardEntity();
        rewWithNullId.setRequiredLevel(new LevelEntity());
        assertNull(rewardMapper.toDomain(rewWithNullId).getRequiredLevelId());
        assertNull(rewardMapper.rewardToLevelEntity(null));

        assertNull(streakMapper.toDomain(null));
        assertNull(streakMapper.toEntity(null));
        assertNotNull(streakMapper.toDomain(new StreakEntity()));
        StreakEntity streakWithNullId = new StreakEntity();
        streakWithNullId.setChild(new ChildProfileEntity());
        assertNull(streakMapper.toDomain(streakWithNullId).getChildId());
        assertNull(streakMapper.streakToChildProfileEntity(null));

        assertNull(userMapper.toDomain(null));
        assertNull(userMapper.toEntity(null));

        assertNull(xpMapper.toDomain(null));
        assertNull(xpMapper.toEntity(null));
        assertNotNull(xpMapper.toDomain(new XPTransactionEntity()));
        XPTransactionEntity xpWithNullId = new XPTransactionEntity();
        xpWithNullId.setChild(new ChildProfileEntity());
        assertNull(xpMapper.toDomain(xpWithNullId).getChildId());
        assertNull(xpMapper.xPTransactionToChildProfileEntity(null));

        // Invocar métodos privados generados por MapStruct con null
        Object[] mappers = {attemptMapper, activityMapper, childAchievementMapper, childProfileMapper, childRewardMapper,
                ecosystemMapper, guardianChildMapper, lessonMapper, progressMapper, rewardMapper, streakMapper, xpMapper};
        for (Object m : mappers) {
            for (Method method : m.getClass().getDeclaredMethods()) {
                if (method.getName().startsWith("entity") && method.getParameterCount() == 1) {
                    method.setAccessible(true);
                    assertNull(method.invoke(m, new Object[]{null}));
                }
            }
        }
    }

    @Test
    @DisplayName("Todas las entidades deben ejecutar prePersist y preUpdate correctamente con valores nulos y no nulos")
    void testEntitiesPrePersistAndPreUpdate() {
        AchievementEntity ach = new AchievementEntity();
        ach.prePersist();
        ach.prePersist();
        assertTrue(ach.getIsActive());

        ActivityAttemptEntity att = new ActivityAttemptEntity();
        att.prePersist();
        att.prePersist();
        assertNotNull(att.getAttemptedAt());

        ActivityEntity act = new ActivityEntity();
        act.prePersist();
        act.prePersist();
        assertEquals(0, act.getXpReward());

        ChildAchievementEntity ca = new ChildAchievementEntity();
        ca.prePersist();
        ca.prePersist();
        assertNotNull(ca.getUnlockedAt());

        ChildProfileEntity cp = new ChildProfileEntity();
        cp.prePersist();
        cp.prePersist();
        cp.preUpdate();
        assertEquals(0, cp.getTotalXp());

        ChildRewardEntity cr = new ChildRewardEntity();
        cr.prePersist();
        cr.prePersist();
        assertFalse(cr.getIsEquipped());

        EcosystemEntity eco = new EcosystemEntity();
        eco.prePersist();
        eco.prePersist();
        assertTrue(eco.getIsActive());

        GuardianChildEntity gc = new GuardianChildEntity();
        gc.prePersist();
        gc.prePersist();
        assertFalse(gc.getIsPrimary());

        LessonEntity lesson = new LessonEntity();
        lesson.prePersist();
        lesson.prePersist();
        assertTrue(lesson.getIsActive());

        ProgressEntity prog = new ProgressEntity();
        prog.prePersist();
        prog.prePersist();
        prog.preUpdate();
        assertEquals(0, prog.getProgressPercentage());

        RewardEntity rew = new RewardEntity();
        rew.prePersist();
        rew.prePersist();
        assertTrue(rew.getIsActive());

        StreakEntity streak = new StreakEntity();
        streak.prePersist();
        streak.prePersist();
        assertEquals(1, streak.getConsecutiveDay());

        UserEntity user = new UserEntity();
        user.prePersist();
        user.prePersist();
        user.preUpdate();
        assertEquals(UserStatus.ACTIVE, user.getStatus());

        XPTransactionEntity xp = new XPTransactionEntity();
        xp.prePersist();
        xp.prePersist();
        assertNotNull(xp.getCreatedAt());
    }
}
