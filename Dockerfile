FROM eclipse-temurin:11-jdk
WORKDIR /app
COPY . .
CMD ["java", "-jar", "target/ai-cloud-api-1.0-SNAPSHOT.jar"]