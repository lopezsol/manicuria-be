FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/profesionales-servicio-0.0.1.jar
COPY ${JAR_FILE} app_profesionales.jar
ENTRYPOINT ["java", "-jar", "app_profesionales.jar"]