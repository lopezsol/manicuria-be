FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/turnos-servicio-0.0.1.jar
COPY ${JAR_FILE} app_turnos.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app_turnos.jar"]