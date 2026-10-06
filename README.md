# 🌍 Guardianes del Planeta - Backend API

Proyecto backend desarrollado con **Spring Boot 3**, **Java 17**, arquitectura **hexagonal (Puertos y Adaptadores)**, persistencia en **MySQL**, autenticación **JWT** y empaquetado **Docker multi-stage**.

---

## 🏛️ Arquitectura Hexagonal (Puertos y Adaptadores)

La estructura desacopla completamente el núcleo de negocio de la tecnología y frameworks externos:

```
src/main/java/com/guardianesdelplaneta/
├── GuardianesPlanetaApplication.java         # Clase principal Spring Boot
│
├── domain/                                   # 🔵 NÚCLEO: Reglas de negocio puras (sin JPA ni frameworks)
│   ├── model/                                # Modelos de dominio y Enums
│   │   ├── User.java                         # Usuario central (roles GUARDIAN y CHILD)
│   │   ├── ChildProfile.java                 # Perfil educativo del niño (edad calculada dinámicamente)
│   │   ├── GuardianChild.java                # Relación N:M Acudiente - Niño
│   │   ├── Ecosystem.java                    # Ecosistemas disponibles (referencia mascotKey)
│   │   ├── Lesson.java                       # Lecciones por ecosistema (1:N)
│   │   ├── Activity.java                     # Actividades interactivas de cada lección (1:N)
│   │   ├── Progress.java                     # Avance del niño por lección (N:M con constraint único)
│   │   ├── ActivityAttempt.java              # Historial de intentos en actividades
│   │   ├── Achievement.java                  # Catálogo de logros (conditionType, visualKey)
│   │   ├── ChildAchievement.java             # Logros desbloqueados por niño (N:M)
│   │   ├── Level.java                        # Catálogo configurable de niveles del sistema
│   │   ├── Reward.java                       # Catálogo de recompensas (avatar, marco, fondo)
│   │   ├── ChildReward.java                  # Recompensas desbloqueadas y equipadas (N:M)
│   │   ├── XPTransaction.java                # Auditoría e histórico de movimientos de XP
│   │   ├── Streak.java                       # Registro diario para cálculo de rachas
│   │   └── enums/                            # Enums de dominio (UserRole, UserStatus, etc.)
│   ├── port/
│   │   ├── in/                               # Puertos de Entrada (Casos de uso)
│   │   └── out/                              # Puertos de Salida (Contratos hacia BD / externos)
│   └── exception/                            # Excepciones de dominio
│
├── application/                              # 🟢 APLICACIÓN: Orquestación de casos de uso
│   └── service/                              # Implementaciones de servicios
│
└── infrastructure/                           # 🟠 INFRAESTRUCTURA: Adaptadores y frameworks
    ├── adapter/
    │   ├── in/rest/                          # Adaptadores de Entrada (REST API)
    │   │   ├── controller/                   # Controladores REST API
    │   │   ├── dto/                          # DTOs como Java Records para Request y Response
    │   │   ├── mapper/                       # Mappers MapStruct (Domain <-> REST DTOs)
    │   │   └── exception/                    # GlobalExceptionHandler
    │   └── out/persistence/                  # Adaptadores de Salida (MySQL & Spring Data JPA)
    │       ├── entity/                       # Entidades JPA (UserEntity, ChildProfileEntity, etc.)
    │       ├── repository/                   # Repositorios Spring Data JPA (15 repositorios)
    │       └── mapper/                       # Mappers MapStruct (Entity <-> Domain)
    └── security/                             # Spring Security 6 & JWT
        ├── config/SecurityConfig.java
        └── jwt/JwtTokenProvider.java, JwtAuthenticationFilter.java
```

---

## 🛠️ Tecnologías y Dependencias

- **Java 17** (LTS)
- **Spring Boot 3.3.4**
  - `spring-boot-starter-web`: Creación de APIs RESTful.
  - `spring-boot-starter-data-jpa`: Conexión y persistencia con JPA / Hibernate.
  - `spring-boot-starter-security`: Seguridad basada en filtros.
  - `spring-boot-starter-validation`: Validación declarativa de datos con Jakarta Bean Validation.
- **MySQL Connector/J** (`com.mysql:mysql-connector-j`): Driver de conexión a MySQL 8.
- **JJWT (io.jsonwebtoken 0.12.6)**: Generación, firma HMAC-SHA256 y verificación de tokens JWT.
- **Lombok**: Reducción de código repetitivo (Getters, Constructores, etc.).
- **Logback & SLF4J**: Registro estructurado de logs configurado en `logback-spring.xml`.
- **Gradle 8.10.2** (Gradle Wrapper incluido).

---

## ⚙️ Variables de Entorno

El proyecto se configura mediante variables de entorno en el archivo `.env` o en `src/main/resources/application.yml`:

| Variable | Descripción | Valor por defecto |
| :--- | :--- | :--- |
| `SERVER_PORT` | Puerto de escucha del backend | `8080` |
| `DB_HOST` | Host del servidor MySQL | `localhost` (o `mysqldb` en Docker) |
| `DB_PORT` | Puerto del servidor MySQL | `3306` |
| `DB_NAME` | Nombre de la base de datos | `guardianes_planeta_db` |
| `DB_USER` | Usuario de la base de datos | `guardian_user` |
| `DB_PASSWORD` | Contraseña del usuario | `guardian_secret_pass` |
| `MYSQL_ROOT_PASSWORD` | Contraseña del usuario root de MySQL | `root_secure_password` |
| `JPA_DDL_AUTO` | Estrategia DDL de Hibernate | `update` |
| `SHOW_SQL` | Imprimir sentencias SQL en logs | `true` |
| `JWT_SECRET` | Clave secreta para firma HMAC-SHA256 | `404E6352...` |
| `JWT_EXPIRATION_MS` | Tiempo de vida del JWT (milisegundos) | `86400000` (24 horas) |

---

## 🚀 Despliegue con Docker Compose (Recomendado)

El archivo `docker-compose.yml` inicia tanto MySQL como el backend utilizando una construcción **Multi-Stage** (etapa de compilación con Gradle + JDK 17 y etapa de ejecución ligera con JRE 17 y usuario no privilegiado):

```bash
docker compose up -d --build
```

Para consultar los logs:
```bash
docker compose logs -f backend
```

Para detener los servicios:
```bash
docker compose down
```

---

## 💻 Ejecución Local

### Prerrequisitos
- Tener una instancia de MySQL corriendo en el puerto `3306` con la base de datos `guardianes_planeta_db` (o levantar solo MySQL con Docker: `docker compose up -d mysqldb`).

### Compilar y Ejecutar Pruebas
```bash
./gradlew test
```

### Ejecutar la Aplicación
```bash
./gradlew bootRun
```

---

## 🩺 Endpoints del Sistema

### 1. Registro de Usuario (Acudiente o Niño)
- **Método**: `POST`
- **URL**: `http://localhost:8080/auth/v1/user`
- **Request Body**:
```json
{
  "username": "guardian_carlos",
  "password": "passwordSeguro123",
  "role": "GUARDIAN"
}
```
- **Respuesta (`201 Created`)**:
```json
{
  "id": 1,
  "username": "guardian_carlos",
  "role": "GUARDIAN",
  "status": "ACTIVE",
  "createdAt": "2026-10-04T13:45:00"
}
```

### 2. Autenticación y Login (Emisión de JWT)
- **Método**: `POST`
- **URL**: `http://localhost:8080/auth/v1/login`
- **Request Body**:
```json
{
  "username": "guardian_carlos",
  "password": "passwordSeguro123"
}
```
- **Respuesta (`200 OK`)**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer",
  "username": "guardian_carlos",
  "role": "GUARDIAN",
  "userId": 1,
  "expiresInMs": 86400000
}
```

### 3. Health Check (Estado de la API y conexión a MySQL)
- **Método**: `GET`
- **URL**: `http://localhost:8080/health`
- **Respuesta exitosa (`200 OK`)**:
```json
{
  "application": "Guardianes del Planeta API",
  "status": "UP",
  "databaseConnected": true,
  "databaseEngine": "MySQL",
  "databaseVersion": "8.0.36",
  "message": "Conexión a MySQL exitosa.",
  "timestamp": "2026-10-04T12:00:00"
}
```
