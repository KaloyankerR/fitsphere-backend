FROM openjdk:17-jdk-alpine AS builder

COPY build/libs/fitsphere-backend-1.0-SNAPSHOT.jar /app/fitsphere-backend.jar
COPY db/migration /flyway/sql

WORKDIR /app

ENTRYPOINT ["java", "-jar", "fitsphere-backend.jar"]
