0. you must run your application as below
1. spring.config.additional-location=startup-backend.properties
2. startup keycloack
3. startup vault
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
