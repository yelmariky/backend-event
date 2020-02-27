FROM openjdk:8-jdk-alpine

WORKDIR /app
ADD *.jar backend-event.jar
COPY logback.xml logback.xml
COPY backend.properties backend.properties
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.additional-location=backend.properties","-jar","backend-event.jar"]

## docker build . -t event/backend:v1