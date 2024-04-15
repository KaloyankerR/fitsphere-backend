FROM openjdk:17-jdk-alpine AS builder

COPY build/libs/fitsphere-backend-1.0-SNAPSHOT.jar build/libs/fitsphere-backend-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "build/libs/fitsphere-backend-1.0-SNAPSHOT.jar"]