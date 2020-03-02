**you must run your application as below**
0. mvn clean package
1. cd docker/traefik
2- docker-compose up -d --build  'start traefic'
3- cd docker
4- docker-compose up -d --build --scale application=2
5- to test by postman:http://localhost:8082/auth/realms/lmsys-realm/protocol/openid-connect/token
6- and call microservice by traefik http://application.localhost/event-backend/api/events/typevent/_getAll

