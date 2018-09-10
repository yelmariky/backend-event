package fr.lmsys.backend.event.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

	@Autowired
	private MailService javaMailSender;
	
	public NotificationService(MailService javaMailSender){
		this.javaMailSender = javaMailSender;
	}
	/**
	 * @Value("${mail.from}")
	String from;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	@Qualifier("javasampleapproachMailSender")
	public MailSender emailSender;
	 * @param from
	 * @param to
	 * @param subject
	 * @param text
	 * @throws MailException
	 * @throws InterruptedException
	 */
	
	@Async
	public void sendNotification(String from,String to,String subject,String text) throws MailException, InterruptedException {
		
		
//        //Thread.sleep(10000);
//		
//        logger.info("sending mail to "+to +" .... ");
//        
//        SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(to);
//		mail.setFrom(from);
//		mail.setSubject(subject);
//		mail.setText(text);
		javaMailSender.sendMail(from,to, subject, text);
		
		logger.info("sent mail to "+to +" *** OK");
	}
	
}
