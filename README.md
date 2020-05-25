**you must run your application with docker as below**
```
1- mvn clean package
2- cd docker/traefik
3- docker-compose up -d --build  ##'start traefic'
4- cd docker 
5- docker-compose up -d --build --scale application=2 ##start keycloak,mysql, replicat=2 thanks to [traefik!](https://docs.traefik.io/):-)
6- to test by postman:http://localhost:8082/auth/realms/lmsys-realm/protocol/openid-connect/token
7- and call microservice by traefik http://application.localhost/event-backend/api/events/typevent/_getAll
```

**you must run your application with kubernates as below**
```
1- mvn clean package
2- cd docker
3- docker build -t event/backend:v2 .
4- cd ../src/main/kubernates/deployment
5- kubectl create -f .
6- create realm lmsys-realm on keycloak setting up bu kubernates
6- to test by postman:http://localhost:8080/auth/realms/lmsys-realm/protocol/openid-connect/token
7- and call microservice http://application.localhost/event-backend/api/events/typevent/_getAll with generated token on 6steps
```
