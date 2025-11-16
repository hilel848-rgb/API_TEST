# שלב 1: Build Stage - שימוש באימג' של Maven לבניית הפרויקט
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# שלב 2: Runtime Stage - שימוש באימג' קטן יותר להרצה בלבד
FROM openjdk:17-jre-slim
WORKDIR /app
# העתקת קובץ ה-JAR המובנה משלב ה-Build
COPY --from=build /app/target/ai-cloud-api-1.0-SNAPSHOT.jar app.jar
# הפעלת היישום
CMD ["java", "-jar", "app.jar"]
