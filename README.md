**you must run your application as below**
```
1- mvn clean package
2- cd docker/traefik
3- docker-compose up -d --build  ##'start traefic'
4- cd docker 
5- docker-compose up -d --build --scale application=2 ##start keycloak,mysql, replicat=2 thanks to [traefik!](https://docs.traefik.io/):-)
6- to test by postman:http://localhost:8082/auth/realms/lmsys-realm/protocol/openid-connect/token
7- and call microservice by traefik http://application.localhost/event-backend/api/events/typevent/_getAll
```
