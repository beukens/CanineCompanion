FROM openjdk:17

WORKDIR /app

COPY target/CanineCompanion-0.0.1.jar /app/CanineCompanion-0.0.1.jar

EXPOSE 8080

CMD ["java", "-jar","CanineCompanion-0.0.1.jar"]

FROM maven:3.6.3-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17
ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} CanineCompanion.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","CanineCompanion.jar"]