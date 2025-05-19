FROM openjdk:21-jdk-slim

# Puerto de depuración
EXPOSE 8082 5005

# Copia el JAR
COPY target/*.jar app.jar

# Inicia la aplicación con depuración remota
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]