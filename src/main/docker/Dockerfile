#### Stage 1: Build the application
FROM openjdk:8-jdk-alpine

# Set the current working directory inside the image
WORKDIR /app

VOLUME /tmp



COPY *.jar backend-event.jar
COPY logback.xml logback.xml
COPY backend.properties backend.properties

##ENTRYPOINT ["java","-jar","/backend-event.jar"]

##ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/backend-event.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-Dspring.config.additional-location=backend.properties","-jar","backend-event.jar"]

## docker build . -t event/backend:v1