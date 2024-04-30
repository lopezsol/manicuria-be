FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/empresas-servicio-0.0.1.jar
COPY ${JAR_FILE} app_empresas.jar
ENTRYPOINT ["java", "-jar", "app_empresas.jar"]