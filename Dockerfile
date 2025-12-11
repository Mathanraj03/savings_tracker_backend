# ===== Build Stage =====
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Runtime Stage =====
FROM eclipse-temurin:21-jre

COPY .env /app/.env
ENV SERVER_PORT=8080
ENV DB_HOST=mysql.railway.internal


WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/tracker-backend-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.config.import=optional:file:.env"]

