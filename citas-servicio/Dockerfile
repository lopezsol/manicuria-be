FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/citas-servicio-0.0.1.jar
COPY ${JAR_FILE} app_citas.jar
ENTRYPOINT ["java", "-jar", "app_citas.jar"]