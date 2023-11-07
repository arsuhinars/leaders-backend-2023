
# Build stage
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /backend

COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw clean install -P docker


# Run stage
FROM eclipse-temurin:17-jre-alpine AS run

RUN apk --no-cache add curl

WORKDIR /backend

COPY --from=build /backend/target/*.jar /backend/app.jar

ENTRYPOINT [ "java", "-jar", "/backend/app.jar" ]
