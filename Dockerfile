FROM openjdk:17-jdk-alpine AS builder

WORKDIR /app

COPY build/libs/fitsphere-backend-1.0-SNAPSHOT.jar /app/fitsphere-backend.jar
COPY src/main/resources/application.properties application.properties
#COPY db/migration /flyway/sql

RUN apk --no-cache add bash

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "fitsphere-backend.jar"]
