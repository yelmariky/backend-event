package fr.lmsys.backend.event.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.lmsys.backend.event.modele.Mail;
import fr.lmsys.backend.event.service.impl.UserServiceImpl;

@RestController
//@CrossOrigin
@RequestMapping("/api/mail")
public class MailRest {

	
//	@Autowired
//	private EmailService mailService;
	@Value("${server.frontend}")
	private String urlfrontend;
	
	@Autowired
    public JavaMailSender emailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@RequestMapping(value = "/_send", method = RequestMethod.POST)
	public void sendMail( @RequestBody Mail mail) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(mail.getTo()); 
        message.setSubject(mail.getSubject()); 
        message.setText(mail.getText());
        message.setFrom(mail.getFrom());
        logger.info("begin send message ");
		emailSender.send(message);
		logger.info("begin send message ");
		
		
		return ;
	}


	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> nfeHandler(Exception ex) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}
