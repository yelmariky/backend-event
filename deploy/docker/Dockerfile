FROM openjdk:8-jdk-alpine


WORKDIR /app
ADD *.jar backend-event.jar
RUN mkdir /app/logs

COPY config.properties config.properties
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.additional-location=config.properties","-jar","backend-event.jar"]

## docker build . -t event/backend:v1