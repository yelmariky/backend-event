package fr.lmsys.backend.event.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailBeans {
	
	@Bean
    public JavaMailSender javaMailService() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

 

        javaMailSender.setHost("SSL0.OVH.NET");

        javaMailSender.setPort(587);

        javaMailSender.setUsername("noreply@lebonevenement.fr");

        javaMailSender.setPassword("Kirama117");

 

        javaMailSender.setJavaMailProperties(getMailProperties());

 

        return javaMailSender;

    }

 

    private Properties getMailProperties() {

 

 

        Properties properties = new Properties();

        properties.setProperty("mail.smtp.from", "noreply@lebonevenement.fr");

        properties.setProperty("mail.transport.protocol", "smtp");

        properties.setProperty("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.starttls.enable", "false");

        properties.setProperty("mail.debug", "false");

        return properties;

    }

}
