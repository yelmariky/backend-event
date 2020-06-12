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
3- docker build -t event/backend:v4 .
4- cd ../src/main/kubernates/deployment
5- helm install -f mysql.values mysql-dev bitnami/mysql   ##you must adapt your file mysql.values with your environement
6- helm install -f keycloak.values name_release codecentric/keycloak  #you must adapt your file keycloak.value with your environement
7-  export POD_NAME=$(kgp -l app.kubernetes.io/name=keycloak -ojsonpath="{.items[*]['metadata.name']}")
8- kubectl port-forward --namespace default $POD_NAME 8080  ## to navigate with keycloak and build your realm and client-id for test
7- kubectl create secret generic appli-secret-pub --from-env-file=backend.properties  #fill in this file with right values
5- kubectl create -f .
6- create realm lmsys-realm on keycloak setting up bu kubernates
6- to test by postman:http://localhost:8080/auth/realms/lmsys-realm/protocol/openid-connect/token
7- and call microservice http://localhost/event-backend/api/events/typevent/_getAll with generated token on 6steps
```
