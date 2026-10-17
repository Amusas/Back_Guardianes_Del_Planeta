package com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guardianesdelplaneta.domain.model.*;
import com.guardianesdelplaneta.domain.model.enums.*;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AllRestMappersAndDomainModelsTest {

    private final AchievementRestMapperImpl achievementRestMapper = new AchievementRestMapperImpl();
    private final ActivityRestMapperImpl activityRestMapper = new ActivityRestMapperImpl();
    private final ChildProfileRestMapperImpl childProfileRestMapper = new ChildProfileRestMapperImpl();
    private final EcosystemRestMapperImpl ecosystemRestMapper = new EcosystemRestMapperImpl();
    private final GuardianChildRestMapperImpl guardianChildRestMapper = new GuardianChildRestMapperImpl();
    private final LessonRestMapperImpl lessonRestMapper = new LessonRestMapperImpl();
    private final LevelRestMapperImpl levelRestMapper = new LevelRestMapperImpl();
    private final ProgressRestMapperImpl progressRestMapper = new ProgressRestMapperImpl();
    private final RewardRestMapperImpl rewardRestMapper = new RewardRestMapperImpl();
    private final StreakRestMapperImpl streakRestMapper = new StreakRestMapperImpl();
    private final UserRestMapperImpl userRestMapper = new UserRestMapperImpl();
    private final XPTransactionRestMapperImpl xpRestMapper = new XPTransactionRestMapperImpl();

    @Test
    @DisplayName("Todos los RestMappers deben convertir entre DTOs y modelos de dominio (Camino Feliz)")
    void testAllRestMappers_HappyPath() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDate dob = LocalDate.now().minusYears(9);

        // 1. AchievementRestMapper
        Achievement ach = new Achievement(1L, "FIRST_STEP", "Primer Paso", "Desc", AchievementConditionType.FIRST_ACTIVITY, 1, "KEY", true);
        AchievementResponseDto achResp = achievementRestMapper.toResponseDto(ach);
        assertEquals("FIRST_STEP", achResp.code());

        AchievementRequestDto achReq = new AchievementRequestDto("CODE", "Title", "Desc", AchievementConditionType.XP_REACHED, 100, "KEY", true);
        Achievement achFromDto = achievementRestMapper.toDomain(achReq);
        assertEquals("CODE", achFromDto.getCode());

        ChildAchievement ca = new ChildAchievement(1L, 2L, 3L, now);
        ChildAchievementResponseDto caResp = achievementRestMapper.toResponseDto(ca);
        assertEquals(2L, caResp.childId());

        // 2. ActivityRestMapper
        Activity act = new Activity(1L, 2L, ActivityType.MULTIPLE_CHOICE, "Title", "{}", 10, 1);
        ActivityResponseDto actResp = activityRestMapper.toResponseDto(act);
        assertEquals("Title", actResp.title());

        ActivityRequestDto actReq = new ActivityRequestDto(2L, ActivityType.TRUE_FALSE, "T", "{}", 15, 2);
        Activity actFromDto = activityRestMapper.toDomain(actReq);
        assertEquals(2L, actFromDto.getLessonId());

        ActivityAttempt att = new ActivityAttempt(1L, 2L, 3L, 1, "{}", true, 10, now);
        ActivityAttemptResponseDto attResp = activityRestMapper.toResponseDto(att);
        assertEquals(10, attResp.xpAwarded());

        ActivityAttemptRequestDto attReq = new ActivityAttemptRequestDto(new ObjectMapper().readTree("{\"ans\":1}"));
        ActivityAttempt attFromDto = activityRestMapper.toDomain(attReq);
        assertNotNull(attFromDto);

        // 3. ChildProfileRestMapper
        ChildProfile cp = new ChildProfile(1L, 2L, "Sofia", dob, 200, 2L, 4, now, now);
        ChildProfileResponseDto cpResp = childProfileRestMapper.toResponseDto(cp);
        assertEquals("Sofia", cpResp.name());
        assertEquals(9, cpResp.age());

        ChildProfileCreateRequestDto cpReq = new ChildProfileCreateRequestDto(2L, "Sofia", dob);
        ChildProfile cpFromDto = childProfileRestMapper.toDomain(cpReq);
        assertEquals("Sofia", cpFromDto.getName());

        // 4. EcosystemRestMapper
        Ecosystem eco = new Ecosystem(1L, "PARAMO", "Páramo", "Desc", 1L, "OSO", true);
        EcosystemResponseDto ecoResp = ecosystemRestMapper.toResponseDto(eco);
        assertEquals("PARAMO", ecoResp.code());

        EcosystemRequestDto ecoReq = new EcosystemRequestDto("OCEANO", "Océano", "Desc", 2L, "TORTUGA", true);
        Ecosystem ecoFromDto = ecosystemRestMapper.toDomain(ecoReq);
        assertEquals("OCEANO", ecoFromDto.getCode());

        // 5. GuardianChildRestMapper
        GuardianChild gc = new GuardianChild(1L, 2L, 3L, RelationshipType.FATHER, true, now);
        GuardianChildResponseDto gcResp = guardianChildRestMapper.toResponseDto(gc);
        assertEquals(2L, gcResp.guardianId());

        GuardianChildLinkRequestDto gcReq = new GuardianChildLinkRequestDto(2L, 3L, RelationshipType.FATHER, true);
        GuardianChild gcFromDto = guardianChildRestMapper.toDomain(gcReq);
        assertEquals(3L, gcFromDto.getChildId());

        // 6. LessonRestMapper
        Lesson lesson = new Lesson(1L, 2L, "Lección", "Contenido", 1, 25, true);
        LessonResponseDto lessonResp = lessonRestMapper.toResponseDto(lesson);
        assertEquals("Lección", lessonResp.title());

        LessonRequestDto lessonReq = new LessonRequestDto(2L, "Lección 2", "Cont", 2, 30, true);
        Lesson lessonFromDto = lessonRestMapper.toDomain(lessonReq);
        assertEquals("Lección 2", lessonFromDto.getTitle());

        // 7. LevelRestMapper
        Level level = new Level(1L, 1, "Semilla", 0, "Desc", "KEY");
        LevelResponseDto levelResp = levelRestMapper.toResponseDto(level);
        assertEquals(1, levelResp.levelNumber());

        LevelRequestDto levelReq = new LevelRequestDto(2, "Agua", 500, "Desc", "KEY2");
        Level levelFromDto = levelRestMapper.toDomain(levelReq);
        assertEquals(2, levelFromDto.getLevelNumber());

        // 8. ProgressRestMapper
        Progress prog = new Progress(1L, 2L, 3L, ProgressStatus.COMPLETED, 100, now, now);
        ProgressResponseDto progResp = progressRestMapper.toResponseDto(prog);
        assertEquals(100, progResp.progressPercentage());

        ProgressUpdateRequestDto progReq = new ProgressUpdateRequestDto(2L, 3L, ProgressStatus.IN_PROGRESS, 50);
        Progress progFromDto = progressRestMapper.toDomain(progReq);
        assertEquals(50, progFromDto.getProgressPercentage());

        // 9. RewardRestMapper
        Reward rew = new Reward(1L, "Corona", "Desc", RewardType.ACCESSORY, "KEY", 5L, true);
        RewardResponseDto rewResp = rewardRestMapper.toResponseDto(rew);
        assertEquals("Corona", rewResp.name());

        RewardRequestDto rewReq = new RewardRequestDto("Marco", "Desc", RewardType.FRAME, "KEY2", 2L, true);
        Reward rewFromDto = rewardRestMapper.toDomain(rewReq);
        assertEquals("Marco", rewFromDto.getName());

        ChildReward cr = new ChildReward(1L, 2L, 3L, now, true);
        ChildRewardResponseDto crResp = rewardRestMapper.toResponseDto(cr);
        assertEquals(3L, crResp.rewardId());

        // 10. StreakRestMapper
        Streak streak = new Streak(1L, 2L, LocalDate.now(), 7, now);
        StreakResponseDto streakResp = streakRestMapper.toResponseDto(streak);
        assertEquals(7, streakResp.consecutiveDay());

        // 11. UserRestMapper
        User user = new User(1L, "guardian", "hash", UserRole.GUARDIAN, UserStatus.ACTIVE, now, now);
        UserResponseDto userResp = userRestMapper.toResponseDto(user);
        assertEquals("guardian", userResp.username());

        UserRegisterRequestDto userReq = new UserRegisterRequestDto("nuevo", "pass123", UserRole.GUARDIAN);
        User userFromDto = userRestMapper.toDomain(userReq);
        assertEquals("nuevo", userFromDto.getUsername());

        // 12. XPTransactionRestMapper
        XPTransaction xp = new XPTransaction(1L, 2L, 30, XPTransactionType.LESSON, 5L, "Desc", now);
        XPTransactionResponseDto xpResp = xpRestMapper.toResponseDto(xp);
        assertEquals(30, xpResp.amount());
    }

    @Test
    @DisplayName("Todos los RestMappers deben retornar null cuando reciben null y modelos de dominio deben cubrir casos borde (Camino Triste)")
    void testAllRestMappers_NullAndEdgeCases() {
        assertNull(achievementRestMapper.toResponseDto((Achievement) null));
        assertNull(achievementRestMapper.toDomain(null));
        assertNull(achievementRestMapper.toResponseDto((ChildAchievement) null));

        assertNull(activityRestMapper.toResponseDto((Activity) null));
        assertNull(activityRestMapper.toDomain((ActivityRequestDto) null));
        assertNull(activityRestMapper.toResponseDto((ActivityAttempt) null));
        assertNull(activityRestMapper.toDomain((ActivityAttemptRequestDto) null));

        assertNull(childProfileRestMapper.toResponseDto(null));
        assertNull(childProfileRestMapper.toDomain(null));

        assertNull(ecosystemRestMapper.toResponseDto(null));
        assertNull(ecosystemRestMapper.toDomain(null));

        assertNull(guardianChildRestMapper.toResponseDto(null));
        assertNull(guardianChildRestMapper.toDomain(null));

        assertNull(lessonRestMapper.toResponseDto(null));
        assertNull(lessonRestMapper.toDomain(null));

        assertNull(levelRestMapper.toResponseDto(null));
        assertNull(levelRestMapper.toDomain(null));

        assertNull(progressRestMapper.toResponseDto(null));
        assertNull(progressRestMapper.toDomain(null));

        assertNull(rewardRestMapper.toResponseDto((Reward) null));
        assertNull(rewardRestMapper.toDomain(null));
        assertNull(rewardRestMapper.toResponseDto((ChildReward) null));

        assertNull(streakRestMapper.toResponseDto(null));

        assertNull(userRestMapper.toResponseDto(null));
        assertNull(userRestMapper.toDomain(null));

        assertNull(xpRestMapper.toResponseDto(null));

        // Casos borde en modelos de dominio
        ChildProfile cpNoDob = new ChildProfile(1L, 2L, "Test", null, null, 1L, null, null, null);
        assertNull(cpNoDob.getAge());
        assertEquals(0, cpNoDob.getTotalXp());
        assertEquals(0, cpNoDob.getStreakCount());

        User user = new User(1L, "u", "p", UserRole.CHILD, UserStatus.INACTIVE, null, null);
        assertFalse(user.isActive());
        assertFalse(user.isGuardian());
        assertTrue(user.isChild());

        Progress p = new Progress(1L, 2L, 3L, ProgressStatus.IN_PROGRESS, null, null, null);
        assertFalse(p.isCompleted());
        p.setStatus(ProgressStatus.COMPLETED);
        assertTrue(p.isCompleted());
    }
}
