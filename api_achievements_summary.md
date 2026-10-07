# Achievements and Streaks API Summary

## Achievements

1. `GET /api/v1/achievements`
   - Returns all active achievements.
2. `GET /api/v1/children/me/achievements`
   - Returns the achievements unlocked by the current `CHILD` user (extracted from JWT).
3. `GET /api/v1/children/me/achievements/{achievementId}`
   - Returns the status of a specific achievement for the current `CHILD`.

## Streaks

1. `GET /api/v1/children/me/streak`
   - Returns the current streak info for the logged-in child.
2. `GET /api/v1/children/me/streak/history`
   - Returns the full streak history.

## Architecture & Implementation Details

- **Evaluator**: `AchievementEvaluator` runs at the end of `ActivityAttemptService` inside the same transaction, evaluating gamification events like First Activity, XP Reached, Level Reached, etc.
- **Idempotency**: Prevented duplicate unlocks using `@UniqueConstraint` in `ChildAchievementEntity` (`child_id`, `achievement_id`) and by checking existence before saving.
- **Hexagonal Architecture**: Used Domain Ports (`GetAchievementsUseCase`, `GetStreakUseCase`, `AchievementPersistencePort`) and Adapters (`AchievementController`, `AchievementPersistenceAdapter`) to keep business logic isolated.
- **Security**: The endpoints under `/children/me/*` use `@PreAuthorize("hasRole('CHILD')")`.
- **Response Updates**: `ActivityAttemptResponseDto` now includes a list of freshly unlocked achievements for immediate UI feedback.
