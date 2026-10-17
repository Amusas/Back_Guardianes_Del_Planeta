# ==========================================
# Stage 1: Build stage
# ==========================================
FROM eclipse-temurin:17-jdk-alpine AS builder

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

WORKDIR /app

# Copiar archivos de configuración de Gradle para aprovechar la caché de Docker
COPY gradle gradle
COPY gradlew build.gradle settings.gradle ./

# Dar permisos de ejecución al wrapper
RUN chmod +x gradlew

# Copiar el código fuente y compilar la aplicación
COPY src src
RUN ./gradlew bootJar --no-daemon -x test

# ==========================================
# Stage 2: Runtime stage
# ==========================================
FROM eclipse-temurin:17-jre-alpine AS runner

ENV LANG=C.UTF-8
ENV LC_ALL=C.UTF-8

WORKDIR /app

# Crear un usuario no privilegiado por seguridad
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Copiar el archivo JAR empaquetado desde la etapa de compilación
COPY --from=builder /app/build/libs/*.jar app.jar

# Asignar propiedad de archivos al usuario no root
RUN chown -R appuser:appgroup /app
USER appuser

# Puerto por defecto del backend
EXPOSE 8080

# Opciones de JVM adaptadas a contenedores con UTF-8
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -Dfile.encoding=UTF-8"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
