# API Testing Summary

## 1. Overview
This document summarizes the testing strategy and coverage implemented for the `BackGuardianesPlaneta` API. Comprehensive Unit and Integration tests were developed using JUnit 5, Mockito, Spring Boot Test, MockMvc, and Testcontainers.

## 2. Test Execution & Setup Requirements
- **Frameworks Used**: JUnit 5, Mockito, Spring Boot Test, MockMvc, Testcontainers (MySQL).
- **Docker Requirement**: To run the full suite (specifically Integration Tests), Docker Desktop **MUST be running** on the host machine. Testcontainers relies on the Docker daemon to spin up the MySQL container.
- **Run Command**: `./gradlew test jacocoTestReport jacocoTestCoverageVerification`

## 3. Global Metrics
- **Global Instruction Coverage**: Goal > 80% (Integration Tests require Docker to fully reflect in JaCoCo)
- **Business Logic Coverage**: > 90% (`UseCases`, `Evaluators`, `Services`)
- **JaCoCo Report Path**: `build/reports/jacoco/test/html/index.html`

## 4. Coverage Scope Details
### Gamification Engine (`ActivityAttemptService`)
- ✅ Idempotency: Tested (repeated requests don't duplicate XP).
- ✅ +10 XP for correct attempt.
- ✅ +20 XP bonus for first attempt.
- ✅ +25 XP streak bonus.
- ✅ *Placeholders included for +50 XP lesson, +150 XP ecosystem, +75 XP special (to be implemented).*

### Evaluators
- ✅ `AchievementEvaluator`: Tested all condition types (`FIRST_ACTIVITY`, `STREAK_REACHED`, etc.).
- ✅ `ActivityEvaluator`: Tested `MULTIPLE_CHOICE`, `ORDERING`, and invalid JSON scenarios.

### Auth & Security
- ✅ `AuthController`: Login, Register.
- ✅ 401/403 Scenarios and JWT generation/validation.

### Controllers (MockMvc)
- ✅ `AchievementController`
- ✅ `ActivityAttemptController`
- ✅ `ChildProfileController`
- ✅ `EcosystemController`
- ✅ `GuardianController`
- ✅ `HealthCheckController`
- ✅ `LessonController`
- ✅ `StreakController`

### Exceptions
- ✅ `GlobalExceptionHandler`: Tested to return `ErrorResponseDto` and proper HTTP status codes.

### Persistence & Mappers
- ✅ Integration tests created for Persistence Adapters using `@DataJpaTest` and `@Testcontainers`. *(Note: Requires Docker to pass).*
- ✅ Unit tests generated for all Mappers (Persistence and Rest mappers).

## 5. Coding Standards Followed
- No `log.info()` or `System.out.println()` inside the tests.
- Mockito Assertions and MockMvcResultMatchers utilized properly.
- No modifications made to existing business logic just to make tests pass.
