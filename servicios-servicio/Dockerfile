FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/servicios-servicio-0.0.1.jar
COPY ${JAR_FILE} app_servicios.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app_servicios.jar"]