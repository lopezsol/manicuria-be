#FROM openjdk:17-jdk-slim
#ARG JAR_FILE=target/servicios-servicio-0.0.1.jar
#COPY ${JAR_FILE} app_servicios.jar
#ENTRYPOINT ["java", "-jar", "app_servicios.jar"]
FROM openjdk:17-jdk-slim as build

COPY . /app
WORKDIR /app

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN mv -f target/*.jar app.jar

FROM openjdk:17-jdk-slim-jre

ARG PORT
ENV PORT=${PORT}

COPY --from=build /app/app.jar .

RUN useradd runtime
USER runtime

ENTRYPOINT ["java", "-Dserver.port=${PORT}" , "-jar", "app_servicios.jar"]