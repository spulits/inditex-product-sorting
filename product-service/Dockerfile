﻿FROM openjdk:21-jdk-slim

VOLUME /tmp

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]
