FROM openjdk:11-jdk-slim
WORKDIR /app
COPY . .
CMD ["java", "-jar", "ai-cloud-api-1.0-SNAPSHOT.jar"]