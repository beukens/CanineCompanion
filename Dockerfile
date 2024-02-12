

FROM maven:3.6.3-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM openjdk:17
ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} CanineCompanion.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","CanineCompanion.jar"]