FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/imagenes-servicio-0.0.1.jar
COPY ${JAR_FILE} app_imagenes.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "app_imagenes.jar"]