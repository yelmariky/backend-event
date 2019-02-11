package fr.lmsys.backend.event.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Async
	public void sendMail(String from,String to, String subject, String text) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setFrom(from);
		mail.setSubject(subject);
		mail.setText(text);
		javaMailSender.send(mail);
		
		logger.info("sent mail to " + to + " *** OK");
	}

}
