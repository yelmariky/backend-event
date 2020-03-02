0. cd docker/traefik && docker-compose -d --build  
1- cd docker docker-compose up -d --build --scale application=2
2- on postman: http://localhost:8082/auth/realms/lmsys-realm/protocol/openid-connect/token
3- http://application.localhost/event-backend/api/events/typevent/_getAll
you must run your application as below
1. spring.config.additional-location=startup-backend.properties
4. to secure the password define in file properties us this line command
mvn -Djasypt.encryptor.password=supersecretz spring-boot:run# backend-event
5. the content of startup-backend.properties is as below
6. TOKEN_VAULT=your vaul token
7. archive=rep to archive images
8.server.frontend=url frontend
9. mail.sav=your sav mail
10. backend.host=http://localhost:8085

11. mail.from=your_mail from

12. mail.password=@@password@@
13. mail.host=server smtp
14. mail.port=587

15. mail.username=your_mail
16. mail.from=yur_mail_from
