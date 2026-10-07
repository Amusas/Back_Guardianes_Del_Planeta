# API Documentation Summary

The following controllers were updated to include OpenAPI annotations (`@Tag`, `@Operation`, `@ApiResponses`, `@ApiResponse`, `@SecurityRequirement`) for comprehensive Swagger UI documentation:

1. **`AchievementController.java`**: Added annotations. Configured `@SecurityRequirement(name = "bearerAuth")` appropriately.
2. **`ActivityAttemptController.java`**: Added annotations. Configured `@SecurityRequirement(name = "bearerAuth")`.
3. **`AuthController.java`**: Added annotations. Documented as public endpoints without `@SecurityRequirement`.
4. **`ChildProfileController.java`**: Added annotations. Configured `@SecurityRequirement(name = "bearerAuth")`.
5. **`EcosystemController.java`**: Added annotations. Configured `@SecurityRequirement(name = "bearerAuth")`.
6. **`GuardianController.java`**: Added annotations. Configured `@SecurityRequirement(name = "bearerAuth")`.
7. **`HealthCheckController.java`**: Added annotations. Documented as a public endpoint without `@SecurityRequirement`.
8. **`LessonController.java`**: Added annotations. Configured `@SecurityRequirement(name = "bearerAuth")`.
9. **`StreakController.java`**: Added annotations. Configured `@SecurityRequirement(name = "bearerAuth")`.

A new configuration class `OpenAPIConfig.java` was added in `com.guardianesdelplaneta.infrastructure.security.config` to provide the global `@OpenAPIDefinition` and set up the JWT `bearerAuth` scheme.

The project builds successfully without any errors. All business logic remains untouched.
