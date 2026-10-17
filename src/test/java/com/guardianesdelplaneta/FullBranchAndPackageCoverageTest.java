package com.guardianesdelplaneta;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guardianesdelplaneta.application.service.*;
import com.guardianesdelplaneta.domain.exception.*;
import com.guardianesdelplaneta.domain.model.*;
import com.guardianesdelplaneta.domain.model.enums.*;
import com.guardianesdelplaneta.domain.port.in.*;
import com.guardianesdelplaneta.domain.port.out.*;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.controller.*;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.dto.*;
import com.guardianesdelplaneta.infrastructure.adapter.in.rest.mapper.*;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.entity.*;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.mapper.ChildProfilePersistenceMapper;
import com.guardianesdelplaneta.infrastructure.adapter.out.persistence.repository.*;
import com.guardianesdelplaneta.infrastructure.security.config.OpenAPIConfig;
import com.guardianesdelplaneta.infrastructure.security.config.SecurityConfig;
import com.guardianesdelplaneta.infrastructure.security.jwt.JwtAuthenticationFilter;
import com.guardianesdelplaneta.infrastructure.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.cors.CorsConfigurationSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class FullBranchAndPackageCoverageTest {

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Domain Models: constructores parametrizados con campos por defecto nulos y Excepciones de Dominio")
    void testDomainModelsNullDefaultsAndExceptions() {
        Ecosystem eco = new Ecosystem(1L, "CODE", "Name", "Desc", 1L, "KEY", null);
        assertTrue(eco.getIsActive());

        Lesson lesson = new Lesson(1L, 1L, "Title", "Content", 1, null, null);
        assertEquals(0, lesson.getXpReward());
        assertTrue(lesson.getIsActive());

        XPTransaction xp = new XPTransaction(1L, 1L, 10, XPTransactionType.ACTIVITY, 1L, "Desc", null);
        assertNotNull(xp.getCreatedAt());

        ChildAchievement ca = new ChildAchievement(1L, 1L, 1L, null);
        assertNotNull(ca.getUnlockedAt());

        Streak streak = new Streak(1L, 1L, LocalDate.now(), null, null);
        assertEquals(1, streak.getConsecutiveDay());
        assertNotNull(streak.getCreatedAt());

        Activity act = new Activity(1L, 1L, ActivityType.MULTIPLE_CHOICE, "Title", "{}", null, 1);
        assertEquals(0, act.getXpReward());

        ActivityAttempt att = new ActivityAttempt(1L, 1L, 1L, 1, "{}", null, null, LocalDateTime.now());
        assertFalse(att.getIsCorrect());
        assertEquals(0, att.getXpAwarded());

        ChildReward cr = new ChildReward(1L, 1L, 1L, null, null);
        assertNotNull(cr.getUnlockedAt());
        assertFalse(cr.getIsEquipped());

        Achievement ach = new Achievement(1L, "CODE", "Title", "Desc", AchievementConditionType.FIRST_LESSON, 1, "KEY", null);
        assertTrue(ach.getIsActive());

        Reward reward = new Reward(1L, "Name", "Desc", RewardType.ACCESSORY, "KEY", 1L, null);
        assertTrue(reward.getIsActive());

        GuardianChild gc = new GuardianChild(1L, 1L, 1L, RelationshipType.MOTHER, null, LocalDateTime.now());
        assertFalse(gc.getIsPrimary());

        DomainException de = new DomainException("Error", new RuntimeException("Cause"));
        assertEquals("Error", de.getMessage());
        assertNotNull(de.getCause());

        AchievementNotFoundException anfe = new AchievementNotFoundException("No encontrado");
        assertEquals("No encontrado", anfe.getMessage());

        ErrorResponseDto err1 = new ErrorResponseDto(400, "Bad Request", "Msg", "/path", LocalDateTime.now());
        ErrorResponseDto err2 = ErrorResponseDto.of(404, "Not Found", "Msg", "/path");
        ErrorResponseDto err3 = ErrorResponseDto.of(422, "Unprocessable", "Msg", "/path", List.of("Detail"));
        assertEquals(400, err1.statusCode());
        assertEquals(404, err2.statusCode());
        assertEquals(1, err3.details().size());
    }

    @Test
    @DisplayName("SecurityConfig, OpenAPIConfig y JwtTokenProvider: cubrir todos los métodos y ramas")
    void testSecurityConfigAndJwtTokenProvider() throws Exception {
        OpenAPIConfig openAPIConfig = new OpenAPIConfig();
        assertNotNull(openAPIConfig);

        JwtTokenProvider tokenProvider = new JwtTokenProvider(
                "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970", 86400000L);
        assertEquals(86400000L, tokenProvider.getExpirationMs());

        String tokenNoClaims = tokenProvider.generateToken("mateo");
        assertTrue(tokenProvider.validateToken(tokenNoClaims));
        assertEquals("mateo", tokenProvider.getUsernameFromToken(tokenNoClaims));
        assertNull(tokenProvider.getUserIdFromToken(tokenNoClaims));
        assertNull(tokenProvider.getRoleFromToken(tokenNoClaims));

        String tokenWithClaims = tokenProvider.generateToken("acudiente", Map.of("userId", 42L, "role", "GUARDIAN"));
        assertEquals(42L, tokenProvider.getUserIdFromToken(tokenWithClaims));
        assertEquals("GUARDIAN", tokenProvider.getRoleFromToken(tokenWithClaims));

        assertFalse(tokenProvider.validateToken("invalid.jwt.token"));
        assertFalse(tokenProvider.validateToken(""));

        JwtTokenProvider expiredProvider = new JwtTokenProvider(
                "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970", -10000L);
        String expiredToken = expiredProvider.generateToken("mateo");
        assertFalse(tokenProvider.validateToken(expiredToken));

        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(tokenProvider);
        SecurityConfig securityConfig = new SecurityConfig(filter);
        assertNotNull(securityConfig.passwordEncoder());

        AuthenticationConfiguration authConfig = mock(AuthenticationConfiguration.class);
        AuthenticationManager authManager = mock(AuthenticationManager.class);
        when(authConfig.getAuthenticationManager()).thenReturn(authManager);
        assertEquals(authManager, securityConfig.authenticationManager(authConfig));

        CorsConfigurationSource corsSource = securityConfig.corsConfigurationSource();
        assertNotNull(corsSource.getCorsConfiguration(new MockHttpServletRequest()));

        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);
        when(http.csrf(any())).thenAnswer(inv -> {
            org.springframework.security.config.Customizer c = inv.getArgument(0);
            c.customize(mock(org.springframework.security.config.annotation.web.configurers.CsrfConfigurer.class));
            return http;
        });
        when(http.cors(any())).thenAnswer(inv -> {
            org.springframework.security.config.Customizer c = inv.getArgument(0);
            c.customize(mock(org.springframework.security.config.annotation.web.configurers.CorsConfigurer.class));
            return http;
        });
        when(http.sessionManagement(any())).thenAnswer(inv -> {
            org.springframework.security.config.Customizer c = inv.getArgument(0);
            c.customize(mock(org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer.class));
            return http;
        });
        when(http.authorizeHttpRequests(any())).thenAnswer(inv -> {
            org.springframework.security.config.Customizer c = inv.getArgument(0);
            var reg = mock(org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry.class, RETURNS_DEEP_STUBS);
            c.customize(reg);
            return http;
        });
        when(http.addFilterBefore(any(), any())).thenReturn(http);
        securityConfig.securityFilterChain(http);
    }

    @Test
    @DisplayName("Controladores REST: cubrir todas las ramas de extracción de credenciales y endpoints faltantes")
    void testControllersAllBranches() {
        // 1. GuardianController
        GetGuardianChildrenUseCase getChildrenUC = mock(GetGuardianChildrenUseCase.class);
        RegisterChildForGuardianUseCase regChildUC = mock(RegisterChildForGuardianUseCase.class);
        GetGuardianChildDashboardUseCase dashUC = mock(GetGuardianChildDashboardUseCase.class);
        ChildProfileRestMapper cpMapper = new ChildProfileRestMapperImpl();
        UserRepository userRepo = mock(UserRepository.class);
        GuardianController guardianController = new GuardianController(getChildrenUC, regChildUC, dashUC, cpMapper, userRepo);

        // Sin autenticación -> ForbiddenAccessException
        SecurityContextHolder.clearContext();
        assertThrows(ForbiddenAccessException.class, guardianController::getMyChildren);

        // Credenciales numéricas + getChildDashboard
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("guardian", 10L));
        GuardianChildDashboardDto mockDash = mock(GuardianChildDashboardDto.class);
        when(dashUC.getChildDashboard(10L, 5L)).thenReturn(mockDash);
        assertEquals(mockDash, guardianController.getChildDashboard(5L).getBody());

        // Credenciales String numéricas válidas
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("guardian", "12"));
        when(getChildrenUC.getChildrenByGuardianId(12L)).thenReturn(List.of());
        assertEquals(200, guardianController.getMyChildren().getStatusCode().value());

        // Credenciales String no numéricas -> cae a búsqueda por username en userRepository (encontrado)
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("guardian_user", "not-a-number"));
        UserEntity uEntity = new UserEntity();
        uEntity.setId(99L);
        when(userRepo.findByUsername("guardian_user")).thenReturn(Optional.of(uEntity));
        assertEquals(200, guardianController.getMyChildren().getStatusCode().value());

        // Credenciales String en blanco y usuario no encontrado en userRepository -> ForbiddenAccessException
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("unknown_user", "   "));
        when(userRepo.findByUsername("unknown_user")).thenReturn(Optional.empty());
        assertThrows(ForbiddenAccessException.class, guardianController::getMyChildren);

        // Username en blanco y credenciales nulas -> ForbiddenAccessException
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("   ", null));
        assertThrows(ForbiddenAccessException.class, guardianController::getMyChildren);

        org.springframework.security.core.Authentication mockAuthNullName = mock(org.springframework.security.core.Authentication.class);
        when(mockAuthNullName.getCredentials()).thenReturn(null);
        when(mockAuthNullName.getName()).thenReturn(null);
        SecurityContextHolder.getContext().setAuthentication(mockAuthNullName);
        assertThrows(ForbiddenAccessException.class, guardianController::getMyChildren);

        // 2. AchievementController
        GetAchievementsUseCase achUC = mock(GetAchievementsUseCase.class);
        AchievementController achController = new AchievementController(achUC, new AchievementRestMapperImpl());

        SecurityContextHolder.clearContext();
        achController.getMyAchievements();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("25", 15L));
        achController.getMyAchievements();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("25", "15"));
        achController.getMyAchievements();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("25", "not-num"));
        achController.getMyAchievements();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("not-num", "   "));
        achController.getMyAchievements();

        SecurityContextHolder.getContext().setAuthentication(mockAuthNullName);
        achController.getMyAchievements();

        // 3. StreakController
        GetStreakUseCase streakUC = mock(GetStreakUseCase.class);
        StreakController streakController = new StreakController(streakUC, new StreakRestMapperImpl());

        SecurityContextHolder.clearContext();
        streakController.getCurrentStreak();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("25", 15L));
        streakController.getCurrentStreak();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("25", "15"));
        streakController.getCurrentStreak();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("25", "not-num"));
        streakController.getCurrentStreak();

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("not-num", "   "));
        streakController.getCurrentStreak();

        SecurityContextHolder.getContext().setAuthentication(mockAuthNullName);
        streakController.getStreakHistory();

        // 4. LessonController (getActivitiesByLessonId, completeLesson & getMyCompletedLessons con Number y null)
        GetLessonsUseCase lessonsUC = mock(GetLessonsUseCase.class);
        GetActivitiesUseCase actsUC = mock(GetActivitiesUseCase.class);
        CompleteLessonUseCase completeUC = mock(CompleteLessonUseCase.class);
        GetChildProgressUseCase progUC = mock(GetChildProgressUseCase.class);
        LessonController lessonController = new LessonController(
                lessonsUC, new LessonRestMapperImpl(), actsUC, new ActivityRestMapperImpl(),
                completeUC, progUC, new ProgressRestMapperImpl());

        when(actsUC.getActivitiesByLessonId(1L)).thenReturn(List.of(
                new Activity(1L, 1L, ActivityType.MULTIPLE_CHOICE, "A1", "{}", 10, 1)));
        assertEquals(1, lessonController.getActivitiesByLessonId(1L).getBody().size());

        var authWithNum = new UsernamePasswordAuthenticationToken("child", 7L);
        var authWithNull = new UsernamePasswordAuthenticationToken("child", null);
        when(completeUC.completeLesson(any(), eq(1L))).thenReturn(
                new Progress(1L, 1L, 1L, ProgressStatus.COMPLETED, 100, LocalDateTime.now(), LocalDateTime.now()));
        when(progUC.getCompletedLessonIdsByUserId(any())).thenReturn(List.of(1L, 2L));

        assertEquals(200, lessonController.completeLesson(1L, authWithNum).getStatusCode().value());
        assertThrows(NullPointerException.class, () -> lessonController.completeLesson(1L, authWithNull));
        assertEquals(200, lessonController.getMyCompletedLessons(authWithNum).getStatusCode().value());
        assertThrows(NullPointerException.class, () -> lessonController.getMyCompletedLessons(authWithNull));

        // 5. ChildProfileController con credenciales nulas
        GetChildProfileUseCase cpUC = mock(GetChildProfileUseCase.class);
        ChildProfileController cpController = new ChildProfileController(cpUC, cpMapper);
        SecurityContextHolder.getContext().setAuthentication(authWithNull);
        assertThrows(NullPointerException.class, cpController::getMyProfile);
    }

    @Test
    @DisplayName("Servicios de Aplicación: cubrir todas las ramas de negocio en LessonProgressService, AchievementService, StreakService, AchievementEvaluator, ActivityAttemptService, etc.")
    void testApplicationServicesAllBranches() {
        // 1. LessonProgressService
        ChildProfilePersistencePort cpPort = mock(ChildProfilePersistencePort.class);
        LessonPersistencePort lessonPort = mock(LessonPersistencePort.class);
        ProgressPersistencePort progPort = mock(ProgressPersistencePort.class);
        AchievementEvaluator achEval = mock(AchievementEvaluator.class);
        LessonProgressService lessonProgressService = new LessonProgressService(cpPort, lessonPort, progPort, achEval);

        ChildProfile child = new ChildProfile(1L, 10L, "Mateo", LocalDate.now().minusYears(8), 100, 2L, 3, LocalDateTime.now(), LocalDateTime.now());
        when(cpPort.findByUserId(10L)).thenReturn(Optional.of(child));
        when(cpPort.findByUserId(99L)).thenReturn(Optional.empty());

        // Camino triste: niño no encontrado en getProgressByUserId
        assertThrows(ChildProfileNotFoundException.class, () -> lessonProgressService.getProgressByUserId(99L));

        // getCompletedLessonIdsByUserId con progreso COMPLETED e IN_PROGRESS
        Progress pCompleted = new Progress(1L, 1L, 100L, ProgressStatus.COMPLETED, 100, LocalDateTime.now(), LocalDateTime.now());
        Progress pInProgress = new Progress(2L, 1L, 101L, ProgressStatus.IN_PROGRESS, 50, null, LocalDateTime.now());
        when(progPort.findByChildId(1L)).thenReturn(List.of(pCompleted, pInProgress));
        List<Long> completedIds = lessonProgressService.getCompletedLessonIdsByUserId(10L);
        assertEquals(List.of(100L), completedIds);

        // completeLesson con progreso ya completado (completedAt != null) y ecosystemId == null
        Lesson lessonNoEco = new Lesson(100L, null, "L1", "C", 1, 10, true);
        when(lessonPort.findById(100L)).thenReturn(Optional.of(lessonNoEco));
        when(progPort.findByChildIdAndLessonId(1L, 100L)).thenReturn(Optional.of(pCompleted));
        when(progPort.save(any())).thenAnswer(inv -> inv.getArgument(0));
        lessonProgressService.completeLesson(10L, 100L);

        // completeLesson con ecosystemId != null y todas las lecciones del ecosistema completadas
        Lesson lessonEco = new Lesson(100L, 5L, "L1", "C", 1, 10, true);
        when(lessonPort.findById(100L)).thenReturn(Optional.of(lessonEco));
        when(lessonPort.findActiveLessonsByEcosystemId(5L)).thenReturn(List.of(lessonEco));
        lessonProgressService.completeLesson(10L, 100L);

        // completeLesson con ecosystemId != null pero lecciones del ecosistema vacías o incompletas
        Lesson lessonEco2 = new Lesson(102L, 5L, "L2", "C", 2, 10, true);
        when(lessonPort.findActiveLessonsByEcosystemId(5L)).thenReturn(List.of(lessonEco, lessonEco2));
        lessonProgressService.completeLesson(10L, 100L);

        when(lessonPort.findActiveLessonsByEcosystemId(5L)).thenReturn(List.of());
        lessonProgressService.completeLesson(10L, 100L);

        // completeLesson cuando achievementEvaluator lanza excepción (catch)
        when(achEval.evaluateAchievements(any(), anyInt(), anyInt(), anyInt(), anyInt())).thenThrow(new RuntimeException("Eval error"));
        assertDoesNotThrow(() -> lessonProgressService.completeLesson(10L, 100L));

        // 2. AchievementService
        AchievementPersistencePort achPort = mock(AchievementPersistencePort.class);
        ChildAchievementPersistencePort caPort = mock(ChildAchievementPersistencePort.class);
        AchievementService achService = new AchievementService(achPort, caPort, cpPort);

        assertThrows(DomainException.class, () -> achService.getUnlockedAchievements(99L));
        assertThrows(DomainException.class, () -> achService.getAchievementStatus(99L, 1L));

        when(caPort.findByChildId(1L)).thenReturn(List.of(new ChildAchievement(1L, 1L, 50L, LocalDateTime.now())));
        when(achPort.findById(50L)).thenReturn(Optional.empty());
        assertThrows(AchievementNotFoundException.class, () -> achService.getUnlockedAchievements(10L));

        when(caPort.findByChildIdAndAchievementId(1L, 50L)).thenReturn(Optional.empty());
        assertThrows(AchievementNotFoundException.class, () -> achService.getAchievementStatus(10L, 50L));

        // 3. StreakService
        StreakPersistencePort streakPort = mock(StreakPersistencePort.class);
        StreakService streakService = new StreakService(streakPort, cpPort);
        assertThrows(DomainException.class, () -> streakService.getCurrentStreak(99L));
        assertThrows(DomainException.class, () -> streakService.getStreakHistory(99L));

        // 4. AchievementEvaluator: cubrir todas las ramas (ya existe, conditionValue null, condiciones falsas y verdaderas, levelId null)
        AchievementEvaluator realAchEval = new AchievementEvaluator(achPort, caPort);
        List<Achievement> allTypes = List.of(
                new Achievement(1L, "A1", "T", "D", AchievementConditionType.FIRST_ACTIVITY, null, "K", true),
                new Achievement(2L, "A2", "T", "D", AchievementConditionType.FIRST_LESSON, 5, "K", true),
                new Achievement(3L, "A3", "T", "D", AchievementConditionType.ECOSYSTEM_COMPLETED, 2, "K", true),
                new Achievement(4L, "A4", "T", "D", AchievementConditionType.XP_REACHED, 500, "K", true),
                new Achievement(5L, "A5", "T", "D", AchievementConditionType.LEVEL_REACHED, 3, "K", true),
                new Achievement(6L, "A6", "T", "D", AchievementConditionType.STREAK_REACHED, 10, "K", true),
                new Achievement(7L, "A7", "T", "D", AchievementConditionType.SPECIAL_CHALLENGE_COMPLETED, 1, "K", true)
        );
        when(achPort.findAllActive()).thenReturn(allTypes);
        when(caPort.existsByChildIdAndAchievementId(1L, 1L)).thenReturn(true);
        when(caPort.existsByChildIdAndAchievementId(eq(1L), longThat(id -> id != null && id > 1L))).thenReturn(false);

        // Primero con condiciones NO cumplidas y levelId == null
        ChildProfile childNullLevel = new ChildProfile(1L, 10L, "Mateo", LocalDate.now(), 10, null, 1, LocalDateTime.now(), LocalDateTime.now());
        List<Achievement> unlockedNone = realAchEval.evaluateAchievements(childNullLevel, 0, 0, 0, 0);
        assertTrue(unlockedNone.isEmpty());

        // Luego con levelId < condVal
        childNullLevel.setLevelId(1L);
        assertTrue(realAchEval.evaluateAchievements(childNullLevel, 0, 0, 0, 0).isEmpty());

        // Luego con todas las condiciones cumplidas
        when(caPort.existsByChildIdAndAchievementId(anyLong(), anyLong())).thenReturn(false);
        ChildProfile childHigh = new ChildProfile(1L, 10L, "Mateo", LocalDate.now(), 1000, 5L, 15, LocalDateTime.now(), LocalDateTime.now());
        List<Achievement> unlockedAll = realAchEval.evaluateAchievements(childHigh, 5, 10, 3, 2);
        assertEquals(7, unlockedAll.size());

        // 5. CreateUserService & AuthenticateUserService & RegisterChildForGuardianService
        UserPersistencePort userPort = mock(UserPersistencePort.class);
        PasswordEncoderPort encoderPort = mock(PasswordEncoderPort.class);
        CreateUserService createUserService = new CreateUserService(userPort, encoderPort);
        User userWithStatus = new User(null, "u1", null, UserRole.GUARDIAN, UserStatus.INACTIVE, null, null);
        when(userPort.existsByUsername("u1")).thenReturn(false);
        when(encoderPort.encode(anyString())).thenReturn("hashed");
        when(userPort.save(any())).thenAnswer(inv -> inv.getArgument(0));
        User created = createUserService.createUser(userWithStatus, "pass");
        assertEquals(UserStatus.INACTIVE, created.getStatus());

        AuthenticateUserService authService = new AuthenticateUserService(userPort, encoderPort);
        when(userPort.findByUsername("u1")).thenReturn(Optional.of(userWithStatus));
        assertThrows(UserBlockedException.class, () -> authService.authenticate("u1", "pass"));

        UserRepository uRepo = mock(UserRepository.class);
        ChildProfileRepository cpRepo = mock(ChildProfileRepository.class);
        LevelRepository lvlRepo = mock(LevelRepository.class);
        GuardianChildRepository gcRepo = mock(GuardianChildRepository.class);
        ChildProfilePersistenceMapper cpPersistMapper = mock(ChildProfilePersistenceMapper.class);
        RegisterChildForGuardianService regService = new RegisterChildForGuardianService(
                uRepo, encoderPort, cpRepo, lvlRepo, gcRepo, cpPersistMapper);

        UserEntity gEntity = new UserEntity();
        gEntity.setId(1L);
        when(uRepo.findById(1L)).thenReturn(Optional.of(gEntity));
        when(uRepo.existsByUsername("child_u")).thenReturn(false);
        when(uRepo.save(any())).thenReturn(gEntity);
        when(lvlRepo.findByLevelNumber(1)).thenReturn(Optional.empty());
        when(lvlRepo.findAll()).thenReturn(List.of());
        when(cpRepo.save(any())).thenReturn(new ChildProfileEntity());
        when(cpPersistMapper.toDomain(any())).thenReturn(child);
        assertNotNull(regService.registerChildForGuardian(1L, "child_u", "pass", "Mateo", LocalDate.now().minusYears(8)));
    }

    @Test
    @DisplayName("ActivityEvaluator, ActivityAttemptService y GetGuardianChildDashboardService: cubrir ramas restantes")
    void testEvaluatorAttemptAndDashboardRemainingBranches() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ActivityEvaluator evaluator = new ActivityEvaluator(mapper);

        // Ramas donde config tiene la llave pero response NO tiene la llave esperada
        Activity mc1 = new Activity(1L, 1L, ActivityType.MULTIPLE_CHOICE, "Q", "{\"correctAnswer\":1}", 10, 1);
        assertFalse(evaluator.evaluate(mc1, mapper.readTree("{\"other\":1}")));

        Activity mc2 = new Activity(2L, 1L, ActivityType.MULTIPLE_CHOICE, "Q", "{\"correctOption\":\"A\"}", 10, 1);
        assertFalse(evaluator.evaluate(mc2, mapper.readTree("{\"other\":\"A\"}")));

        Activity ord1 = new Activity(3L, 1L, ActivityType.ORDERING, "Q", "{\"correctSequence\":[1,2]}", 10, 1);
        assertFalse(evaluator.evaluate(ord1, mapper.readTree("{\"other\":[1,2]}")));

        Activity dnd1 = new Activity(4L, 1L, ActivityType.DRAG_AND_DROP, "Q", "{\"correctAnswers\":{\"a\":1}}", 10, 1);
        assertFalse(evaluator.evaluate(dnd1, mapper.readTree("{\"other\":{\"a\":1}}")));

        // ActivityAttemptService ramas faltantes
        ChildProfilePersistencePort cpPort = mock(ChildProfilePersistencePort.class);
        ActivityPersistencePort actPort = mock(ActivityPersistencePort.class);
        ActivityAttemptPersistencePort attPort = mock(ActivityAttemptPersistencePort.class);
        XPTransactionPersistencePort xpPort = mock(XPTransactionPersistencePort.class);
        LevelPersistencePort lvlPort = mock(LevelPersistencePort.class);
        ProgressPersistencePort progPort = mock(ProgressPersistencePort.class);
        StreakPersistencePort streakPort = mock(StreakPersistencePort.class);
        RewardPersistencePort rewPort = mock(RewardPersistencePort.class);
        ChildRewardPersistencePort crPort = mock(ChildRewardPersistencePort.class);
        LessonPersistencePort lessonPort = mock(LessonPersistencePort.class);
        AchievementEvaluator achEval = mock(AchievementEvaluator.class);
        ActivityAttemptService attemptService = new ActivityAttemptService(
                cpPort, actPort, attPort, xpPort, lvlPort, progPort, streakPort, rewPort, crPort, lessonPort,
                evaluator, achEval, new AchievementRestMapperImpl());

        when(cpPort.findByUserId(99L)).thenReturn(Optional.empty());
        ActivityAttemptRequestDto req = new ActivityAttemptRequestDto(mapper.readTree("{\"answer\":1}"));
        assertThrows(DomainException.class, () -> attemptService.submitAttempt(99L, 1L, req));

        ChildProfile child = new ChildProfile(1L, 10L, "Mateo", LocalDate.now(), 100, null, 1, LocalDateTime.now(), LocalDateTime.now());
        when(cpPort.findByUserId(10L)).thenReturn(Optional.of(child));
        when(actPort.findById(99L)).thenReturn(Optional.empty());
        assertThrows(DomainException.class, () -> attemptService.submitAttempt(10L, 99L, req));

        // Intento correcto donde ya existen las transacciones BONUS, yesterdayStreak presente, previousLevelId null, reward ya existe
        when(actPort.findById(1L)).thenReturn(Optional.of(mc1));
        when(attPort.countByChildIdAndActivityId(1L, 1L)).thenReturn(0L);
        when(xpPort.existsByChildIdAndTypeAndReferenceId(anyLong(), any(), anyLong())).thenReturn(true);
        when(streakPort.findByChildIdAndActivityDate(eq(1L), any())).thenAnswer(inv -> {
            LocalDate d = inv.getArgument(1);
            if (d.equals(LocalDate.now(java.time.ZoneId.of("America/Bogota")))) return Optional.empty();
            return Optional.of(new Streak(1L, 1L, d, 4, LocalDateTime.now()));
        });
        Level nextLevel = new Level(2L, 2, "Brote", 50, "D", "K");
        when(lvlPort.findByLevelNumber(2)).thenReturn(Optional.of(nextLevel));
        when(lvlPort.findByLevelNumber(3)).thenReturn(Optional.of(new Level(3L, 3, "Arbol", 5000, "D", "K")));
        Reward r1 = new Reward(10L, "R1", "D", RewardType.ACCESSORY, "K", 2L, true);
        when(rewPort.findByRequiredLevelIdLessThanEqual(2L)).thenReturn(List.of(r1));
        when(crPort.existsByChildIdAndRewardId(1L, 10L)).thenReturn(true);
        when(achEval.evaluateAchievements(any(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(List.of());

        ActivityAttemptResponseDto resp = attemptService.submitAttempt(10L, 1L, req);
        assertTrue(resp.isCorrect());
        assertEquals(5, resp.streakCount());

        // Intento con previousLevelId != null pero levelPort.findById retorna empty
        child.setLevelId(88L);
        when(lvlPort.findById(88L)).thenReturn(Optional.empty());
        attemptService.submitAttempt(10L, 1L, req);

        // GetGuardianChildDashboardService ramas faltantes (nivel nulo, sin siguiente nivel, listas vacías, campos nulos)
        GuardianChildRepository gcRepo = mock(GuardianChildRepository.class);
        ChildProfileRepository cpRepo = mock(ChildProfileRepository.class);
        LevelRepository levelRepo = mock(LevelRepository.class);
        ProgressRepository progRepo = mock(ProgressRepository.class);
        LessonRepository lessonRepo = mock(LessonRepository.class);
        ActivityAttemptRepository attRepo = mock(ActivityAttemptRepository.class);
        XPTransactionRepository xpRepo = mock(XPTransactionRepository.class);
        ChildAchievementRepository caRepo = mock(ChildAchievementRepository.class);
        AchievementRepository achRepo = mock(AchievementRepository.class);
        EcosystemRepository ecoRepo = mock(EcosystemRepository.class);

        GetGuardianChildDashboardService dashService = new GetGuardianChildDashboardService(
                gcRepo, cpRepo, levelRepo, progRepo, lessonRepo, attRepo, xpRepo, caRepo, achRepo, ecoRepo);

        when(gcRepo.existsByGuardianIdAndChildId(1L, 2L)).thenReturn(true);
        ChildProfileEntity bareChild = new ChildProfileEntity();
        bareChild.setId(2L);
        bareChild.setName("Sofía");
        bareChild.setTotalXp(50);
        bareChild.setLevel(null);
        bareChild.setDateOfBirth(null);
        bareChild.setUser(null);
        bareChild.setStreakCount(null);
        when(cpRepo.findById(2L)).thenReturn(Optional.of(bareChild));

        LevelEntity badSpanLevel = new LevelEntity();
        badSpanLevel.setLevelNumber(2);
        badSpanLevel.setName("Nivel 2");
        badSpanLevel.setRequiredXp(0); // xpSpan <= 0
        when(levelRepo.findByLevelNumber(2)).thenReturn(Optional.of(badSpanLevel));

        ProgressEntity inProg = new ProgressEntity();
        inProg.setStatus(ProgressStatus.IN_PROGRESS);
        ProgressEntity compNoDate = new ProgressEntity();
        compNoDate.setStatus(ProgressStatus.COMPLETED);
        compNoDate.setCompletedAt(null);
        LessonEntity lDummy = new LessonEntity();
        lDummy.setId(999L);
        compNoDate.setLesson(lDummy);
        ProgressEntity compNullLesson = new ProgressEntity();
        compNullLesson.setStatus(ProgressStatus.COMPLETED);
        compNullLesson.setCompletedAt(LocalDateTime.now());
        compNullLesson.setLesson(null);
        ProgressEntity compLessonNullFields = new ProgressEntity();
        compLessonNullFields.setStatus(ProgressStatus.COMPLETED);
        compLessonNullFields.setCompletedAt(null); // para probar sort con dos nulls
        LessonEntity emptyLesson = new LessonEntity();
        emptyLesson.setId(888L);
        emptyLesson.setTitle(null);
        emptyLesson.setXpReward(null);
        compLessonNullFields.setLesson(emptyLesson);
        ProgressEntity compLessonNullFieldsWithDate = new ProgressEntity();
        compLessonNullFieldsWithDate.setStatus(ProgressStatus.COMPLETED);
        compLessonNullFieldsWithDate.setCompletedAt(LocalDateTime.now());
        compLessonNullFieldsWithDate.setLesson(emptyLesson);

        when(progRepo.findByChildId(2L)).thenReturn(List.of(inProg, compNoDate, compLessonNullFieldsWithDate));

        ActivityAttemptEntity attNullTitle = new ActivityAttemptEntity();
        ActivityEntity actNullTitle = new ActivityEntity();
        actNullTitle.setTitle(null);
        attNullTitle.setActivity(actNullTitle);
        attNullTitle.setIsCorrect(false);
        attNullTitle.setAttemptedAt(null);

        ActivityAttemptEntity attNullTimestamp2 = new ActivityAttemptEntity();
        attNullTimestamp2.setActivity(null);
        attNullTimestamp2.setIsCorrect(false);
        attNullTimestamp2.setAttemptedAt(null);

        when(attRepo.findByChildId(2L)).thenReturn(List.of(attNullTitle, attNullTimestamp2));

        EcosystemEntity emptyEco = new EcosystemEntity();
        emptyEco.setId(1L);
        emptyEco.setCode("ECO");
        emptyEco.setName("Eco");
        when(ecoRepo.findByIsActiveTrue()).thenReturn(List.of(emptyEco));
        when(lessonRepo.findByEcosystemIdOrderByOrderIndexAsc(1L)).thenReturn(List.of());

        XPTransactionEntity txNullType = new XPTransactionEntity();
        txNullType.setId(1L);
        txNullType.setAmount(10);
        txNullType.setType(null);
        when(xpRepo.findByChildIdOrderByCreatedAtDesc(2L)).thenReturn(List.of(txNullType));

        GuardianChildDashboardDto dashResult = dashService.getChildDashboard(1L, 2L);
        assertEquals("Semilla Guardiana", dashResult.levelName());
        assertNull(dashResult.age());
        assertEquals("", dashResult.username());
        assertEquals(0, dashResult.currentStreak());

        // Probar también sin intentos y sin siguiente nivel
        when(levelRepo.findByLevelNumber(2)).thenReturn(Optional.empty());
        when(attRepo.findByChildId(2L)).thenReturn(List.of());
        when(progRepo.findByChildId(2L)).thenReturn(List.of(compNullLesson));
        GuardianChildDashboardDto dashResult2 = dashService.getChildDashboard(1L, 2L);
        assertEquals(0.0, dashResult2.accuracyPercentage());
        assertNull(dashResult2.nextLevelRequiredXp());
    }
}
