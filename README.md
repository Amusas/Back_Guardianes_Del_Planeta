# 🌍 Guardianes del Planeta — Backend API

API RESTful educativa y gamificada desarrollada con **Spring Boot 3.3.4**, **Java 17**, **Arquitectura Hexagonal (Puertos y Adaptadores)**, persistencia en **MySQL 8**, seguridad stateless con **JWT (JJWT)**, mapeo automático con **MapStruct** y despliegue contenedorizado con **Docker Multi-Stage**.

---

## 🚀 Funcionalidades Principales

El backend soporta los tres procesos de negocio centrales de **Guardianes del Planeta**, enfocados en la biodiversidad de Colombia:

### 1. 🔐 Gestión de Usuarios, Autenticación y Perfiles Infantiles
- **Registro e Inicio de Sesión (`/auth/v1/user`, `/auth/v1/login`)**: Autenticación segura con hash BCrypt y emisión de tokens JWT firmados con HMAC-SHA256 (`GUARDIAN` y `CHILD`).
- **Vinculación Acudiente–Niño (`/api/v1/guardian/children`)**: Creación de perfiles infantiles asociados al acudiente autenticado, asignación de nivel inicial, avatar base y consulta de niños vinculados.
- **Control de Acceso Basado en Roles y Propiedad**: Validación transversal mediante `JwtAuthenticationFilter` y verificación de vínculo padre-hijo para impedir accesos cruzados (`HTTP 403 Forbidden`).

### 2. 🌿 Gestión de Ecosistemas de Colombia y Actividades Educativas
- **7 Ecosistemas Colombianos (`/api/v1/ecosystems`)**:
  1. `BOSQUE_ANDINO` (Bosque Andino — Oso de Anteojos y Palma de Cera)
  2. `PARAMO` (El Páramo — Frailejones y Cóndor de los Andes)
  3. `OCEANO` (Océanos Caribe y Pacífico — Tortuga Marina y Arrecifes)
  4. `SELVA_AMAZONICA` (Selva Amazónica — Delfín Rosado, Jaguar y Danta)
  5. `MANGLAR` (Manglares Colombianos — Cangrejo Azul y Raíces Zancudas)
  6. `BOSQUE_SECO_TROPICAL` (Bosque Seco Tropical — Tití Cabeciblanco y Guayacán)
  7. `LLANOS_SABANA` (Llanos Orientales y Sabana — Chigüiro, Morichales y Orinoquía)
- **56 Lecciones Progresivas (`/api/v1/ecosystems/{id}/lessons`)**: 8 lecciones ordenadas por ecosistema con desbloqueo secuencial.
- **280 Actividades Interactivas (`/api/v1/lessons/{id}/activities`)**: 5 actividades por lección distribuidas en **5 mecánicas pedagógicas**:
  - `MULTIPLE_CHOICE` (Opción múltiple)
  - `TRUE_FALSE` (Verdadero o falso)
  - `ORDERING` (Ordenamiento de secuencias ecológicas)
  - `CLASSIFICATION` (Clasificación en categorías)
  - `DRAG_AND_DROP` (Asociación / arrastrar y soltar)
- **Motor Polimórfico de Evaluación (`ActivityEvaluator`)**: Calificación automática de intentos (`POST /api/v1/activities/attempts`), retroalimentación pedagógica y cálculo de estrellas.

### 3. 🏆 Gestión del Progreso, Gamificación y Dashboard del Acudiente
- **Experiencia (XP) y Ascenso de Nivel (`LessonProgressService`, `ActivityAttemptService`)**: Registro de transacciones de XP (`XPTransaction`), cálculo de progreso por lección (`Progress`) y subida automática de nivel (`Level`).
- **Rachas Diarias (`StreakService`)**: Seguimiento de días consecutivos de aprendizaje y récord histórico (`longestStreak`).
- **Logros y Recompensas (`AchievementEvaluator`, `RewardUnlockService`)**: Desbloqueo automático de insignias ecológicas y catálogo de recompensas de avatar por nivel o logro.
- **Dashboard del Acudiente (`/api/v1/guardian/children/{childId}/dashboard`)**: Métricas consolidadas en tiempo real (XP total, nivel actual, racha, lecciones completadas, precisión de respuestas, avance por ecosistema, actividad reciente e historial de XP).

---

## 🏛️ Estructura del Proyecto (Arquitectura Hexagonal)

La estructura desacopla completamente el núcleo de negocio de los frameworks y adaptadores externos:

```text
src/main/java/com/guardianesdelplaneta/
├── GuardianesPlanetaApplication.java                  # Clase principal Spring Boot
│
├── domain/                                            # 🔵 NÚCLEO DE DOMINIO (Puro, sin dependencias externas)
│   ├── model/                                         # Modelos inmutables (Java Records) y reglas de dominio
│   │   ├── User.java, ChildProfile.java, GuardianChild.java
│   │   ├── Ecosystem.java, Lesson.java, Activity.java
│   │   ├── Progress.java, ActivityAttempt.java, XPTransaction.java
│   │   ├── Level.java, Achievement.java, ChildAchievement.java
│   │   ├── Reward.java, ChildReward.java, Streak.java
│   │   └── enums/                                     # UserRole, UserStatus, ActivityType, ProgressStatus, etc.
│   ├── port/
│   │   ├── in/                                        # Puertos de Entrada (Casos de Uso)
│   │   └── out/                                       # Puertos de Salida (Contratos de persistencia y seguridad)
│   └── exception/                                     # Excepciones de negocio (InvalidCredentials, Unauthorized, etc.)
│
├── application/                                       # 🟢 CAPA DE APLICACIÓN (Orquestación de Casos de Uso)
│   └── service/
│       ├── AuthService.java                           # Registro, login y emisión de sesión
│       ├── RegisterChildForGuardianService.java       # Creación y vinculación de perfiles infantiles
│       ├── EcosystemService.java                      # Consulta de ecosistemas de Colombia
│       ├── GetLessonsService.java                     # Consulta de lecciones por ecosistema
│       ├── GetActivitiesService.java                  # Consulta de actividades por lección
│       ├── ActivityAttemptService.java                # Procesamiento de intentos, XP y logros
│       ├── ActivityEvaluator.java                     # Motor evaluador de los 5 tipos de actividad
│       ├── LessonProgressService.java                 # Avance y completitud de lecciones
│       ├── AchievementEvaluator.java                  # Motor de desbloqueo de insignias
│       ├── StreakService.java                         # Cálculo de rachas diarias consecutivas
│       ├── RewardUnlockService.java                   # Desbloqueo y equipamiento de recompensas
│       ├── ChildProfileService.java                   # Perfil infantil y resumen de estadísticas
│       └── GetGuardianChildDashboardService.java      # Consolidación de métricas para el acudiente
│
└── infrastructure/                                    # 🟠 CAPA DE INFRAESTRUCTURA (Adaptadores y Frameworks)
    ├── adapter/
    │   ├── in/rest/                                   # Adaptadores de Entrada (API RESTful)
    │   │   ├── controller/                            # Auth, Ecosystem, Lesson, Activity, ChildProfile, Guardian, etc.
    │   │   ├── dto/                                   # Contratos JSON de entrada/salida (Java Records)
    │   │   ├── mapper/                                # Mappers MapStruct (Domain <-> REST DTO)
    │   │   └── exception/GlobalExceptionHandler.java  # Manejo centralizado de errores HTTP (@RestControllerAdvice)
    │   └── out/
    │       ├── persistence/                           # Adaptadores de Salida (Spring Data JPA / MySQL)
    │       │   ├── entity/                            # 15 Entidades JPA
    │       │   ├── repository/                        # Repositorios Spring Data JPA
    │       │   ├── mapper/                            # Mappers MapStruct (Entity <-> Domain)
    │       │   └── seeder/                            # Seeders idempotentes (7 ecosistemas, 56 lecciones, 280 actividades)
    │       └── security/                              # Adaptadores BCrypt y JWT
    └── security/                                      # Configuración Spring Security 6 & Filtros JWT
        ├── config/SecurityConfig.java
        └── jwt/JwtTokenProvider.java, JwtAuthenticationFilter.java
```

---

## ⚙️ Variables de Entorno

El proyecto se configura mediante variables de entorno (o valores por defecto en `src/main/resources/application.yml`):

| Variable | Descripción | Valor por defecto |
| :--- | :--- | :--- |
| `SERVER_PORT` | Puerto HTTP del backend | `8080` |
| `DB_HOST` | Host del servidor MySQL | `localhost` (`mysqldb` en Docker) |
| `DB_PORT` | Puerto del servidor MySQL | `3306` |
| `DB_NAME` | Nombre de la base de datos | `guardianes_planeta_db` |
| `DB_USER` | Usuario de la base de datos | `guardian_user` |
| `DB_PASSWORD` | Contraseña de la base de datos | `guardian_secret_pass` |
| `JPA_DDL_AUTO` | Estrategia DDL de Hibernate | `update` |
| `JWT_SECRET` | Clave secreta HMAC-SHA256 | `404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970` |
| `JWT_EXPIRATION_MS` | Tiempo de expiración del token JWT | `86400000` (24 horas) |

---

## ▶️ Cómo Ejecutar la Aplicación

### Opción 1: Con Docker Compose (Recomendado — Levanta MySQL + Backend)
```bash
# Construir e iniciar MySQL y el Backend en segundo plano
docker compose up -d --build

# Ver logs del backend en tiempo real
docker compose logs -f backend

# Detener los contenedores
docker compose down
```

### Opción 2: Ejecución Local con Gradle Wrapper
1. Asegúrate de tener una instancia de **MySQL 8** corriendo en el puerto `3306` (puedes levantar únicamente la base de datos con `docker compose up -d mysqldb`).
2. Ejecuta la aplicación con Gradle:

**En Windows (PowerShell / CMD):**
```powershell
.\gradlew.bat bootRun
```

**En Linux / macOS:**
```bash
./gradlew bootRun
```

Al iniciar, el `DataSeeder` poblará automáticamente de forma idempotente los niveles, logros, recompensas, los **7 ecosistemas de Colombia**, las **56 lecciones** y las **280 actividades educativas**.

---

## 🧪 Cómo Ejecutar las Pruebas y Generar el Reporte de Cobertura (JaCoCo)

El proyecto cuenta con una suite exhaustiva de **pruebas unitarias, funcionales (`MockMvc`) y de persistencia** que validan tanto los caminos felices (*Happy Paths*) como los caminos tristes (*Sad Paths*).

### Ejecutar todas las pruebas y generar el reporte JaCoCo
**En Windows (PowerShell):**
```powershell
.\gradlew.bat test jacocoTestReport
```

**En Linux / macOS:**
```bash
./gradlew test jacocoTestReport
```

### Consultar el reporte interactivo de cobertura
Una vez finalizado el comando, abre en tu navegador el archivo HTML generado en:
```text
build/reports/jacoco/test/html/index.html
```

### 📊 Métricas Actuales de Cobertura Alcanzadas (JaCoCo)
| Métrica | Cobertura Alcanzada | Meta del Proyecto | Estado |
| :--- | :---: | :---: | :---: |
| **Instrucciones (Instructions)** | **99.66%** | > 85.0% | ✅ Superado |
| **Ramas (Branches)** | **98.62%** | > 80.0% | ✅ Superado |
| **Líneas (Lines)** | **99.72%** | > 85.0% | ✅ Superado |
| **Métodos (Methods)** | **99.51%** | > 85.0% | ✅ Superado |
| **Clases (Classes)** | **100.00%** | > 85.0% | ✅ Superado |

*(Todos los paquetes individuales superan ampliamente el 94% de cobertura de instrucciones y el 93% de cobertura de ramas).*

---

## 📡 Principales Endpoints de la API REST

| Método | Endpoint | Descripción | Autenticación |
| :--- | :--- | :--- | :---: |
| `GET` | `/health` | Estado de salud de la API y conexión a MySQL | Público |
| `POST` | `/auth/v1/user` | Registro de usuario (`GUARDIAN` o `CHILD`) | Público |
| `POST` | `/auth/v1/login` | Inicio de sesión y emisión de token JWT | Público |
| `POST` | `/api/v1/guardian/children` | Crear y vincular un perfil infantil al acudiente | Bearer JWT (`GUARDIAN`) |
| `GET` | `/api/v1/guardian/children` | Listar perfiles infantiles del acudiente | Bearer JWT (`GUARDIAN`) |
| `GET` | `/api/v1/guardian/children/{childId}/dashboard` | Métricas detalladas del niño para el Dashboard | Bearer JWT (`GUARDIAN`) |
| `GET` | `/api/v1/ecosystems` | Listar los 7 ecosistemas activos de Colombia | Bearer JWT |
| `GET` | `/api/v1/ecosystems/{id}/lessons` | Obtener las 8 lecciones ordenadas de un ecosistema | Bearer JWT |
| `GET` | `/api/v1/lessons/{lessonId}/activities` | Obtener las 5 actividades interactivas de una lección | Bearer JWT |
| `POST` | `/api/v1/activities/attempts` | Evaluar intento de actividad, otorgar XP y logros | Bearer JWT |
| `POST` | `/api/v1/children/{childId}/lessons/{lessonId}/complete` | Registrar completitud de lección y actualizar progreso | Bearer JWT |
| `GET` | `/api/v1/children/{childId}/progress` | Consultar el progreso de lecciones de un niño | Bearer JWT |
| `GET` | `/api/v1/children/{childId}/profile-summary` | Resumen de nivel, XP, racha y logros del niño | Bearer JWT |
| `GET` | `/api/v1/children/{childId}/rewards` | Consultar recompensas desbloqueadas y equipadas | Bearer JWT |
| `PUT` | `/api/v1/children/{childId}/rewards/{rewardId}/equip` | Equipar una recompensa en el avatar infantil | Bearer JWT |
