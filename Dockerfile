FROM openjdk:17-jdk-slim

WORKDIR /app

COPY gradlew .
COPY gradle ./gradle
COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew clean build --no-daemon

EXPOSE 8080

CMD ["java", "-jar", "build/libs/web-user-service-0.0.1-SNAPSHOT.jar"]