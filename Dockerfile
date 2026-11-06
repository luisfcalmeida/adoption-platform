FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/adoption-platform-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx300m", "-jar", "app.jar"]