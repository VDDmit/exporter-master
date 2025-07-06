FROM ubuntu:latest
LABEL authors="vddmit"

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/exporter-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]