package fr.lmsys.backend.event.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import fr.lmsys.backend.event.jpa.ContactEntity;
import fr.lmsys.backend.event.jpa.UsersEntity;
import fr.lmsys.backend.event.modele.Contact;
import fr.lmsys.backend.event.modele.Mail;
import fr.lmsys.backend.event.modele.User;
import fr.lmsys.backend.event.repositories.ContactRepository;
import fr.lmsys.backend.event.repositories.UserRepository;
import fr.lmsys.backend.event.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ContactRepository contactRepository;
	
	@Value("${mail.sav}")
	String savmail;
	@Value("${mail.from}")
	String from;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private DozerBeanMapper dozerBeanMapper;
	
	@Autowired
	private MailService emailSender;
	@Autowired
	public CryptSh crypter;

	
	@Autowired
	public void setDoze(DozerBeanMapper dozer){
		this.dozerBeanMapper=dozer;
	}

	public User saveUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		user.setIdUsers(UUID.randomUUID().toString());
		UsersEntity userJpa = dozerBeanMapper.map(user, UsersEntity.class);
		logger.info("first mapper: OK");
		// cherche si le mail existe
		if (CollectionUtils.isEmpty(userRepository.findByEmail(userJpa.getEmail()))) {
			logger.debug("findByEmail repo: OK");
			userJpa.setPassword(crypter.generateStorngPasswordHash(user.getPassword()));
			
			UsersEntity userEntity= userRepository.save(userJpa);
			logger.debug("AfterSave repo: OK");
			
			return dozerBeanMapper.map(userEntity, User.class);
		}
		return null;
	}

	public User activateUser(String idUser) {
		List<UsersEntity> usersE = userRepository.findByIdUsers(idUser);
		UsersEntity userEntity = CollectionUtils.isEmpty(usersE) ? null : usersE.get(0);

		if (userEntity != null) {
			userEntity.setActif(true);
			userRepository.save(userEntity);
			return dozerBeanMapper.map(userEntity, User.class);
		} else {
			return null;
		}
	}

	public List<User> getAllUsers() {
		List<UsersEntity> eventty = (List<UsersEntity>) userRepository.findAll();

		return eventty.stream().map(s -> dozerBeanMapper.map(s, User.class)).collect(Collectors.toList());

	}

	public User findUser(String idUser) {
		List<UsersEntity> usersE = userRepository.findByIdUsers(idUser);

		return CollectionUtils.isEmpty(usersE)?null:dozerBeanMapper.map(usersE.get(0), User.class);
		

	}
	
	public User updateUser(User user,boolean isMail) throws AddressException, MessagingException, UnsupportedEncodingException, MappingException  {
		List<UsersEntity> usersE = userRepository.findByEmail(user.getEmail());
		UsersEntity userEntity = CollectionUtils.isEmpty(usersE) ? null : usersE.get(0);

		if (userEntity != null) {
			if(CollectionUtils.isNotEmpty(user.getContacts())){
				dozerBeanMapper.map(user, userEntity);
				Contact contact= user.getContacts().iterator().hasNext()? user.getContacts().iterator().next():null;
				if(Strings.isNullOrEmpty(contact.getIdContact())){
					
					ContactEntity c = dozerBeanMapper.map(user.getContacts().iterator().next(),ContactEntity.class);
					c.setIdContact(UUID.randomUUID().toString());
					c.setDateReception(LocalDate.now());
					c.setDateTraitement(LocalDate.now());
					c.setUserContact(userEntity);
					contactRepository.save(c);
					
					Mail mail = new Mail();  
					mail.setTo(user.getEmail());
					
					mail.setSubject("service après vente");
					mail.setText("Votre demande sera traité dans les meilleurs délais. \n équipe service aprés vente ");
					//envoi de mail vers le client
					emailSender.sendMail(from,user.getEmail(), mail.getSubject(), mail.getText());
					
					//mail.setTo("lmsys.sav@gmail.com");
					/*message = new SimpleMailMessage(); 
			        message.setTo(savmail); 
			        message.setSubject(mail.getSubject()); 
			        message.setText(mail.getText());
			        emailSender.send(message);
			        */
			       // emailSender.sendMail(from, savmail, mail.getSubject(), mail.getText());
			        emailSender.sendMail(from,savmail, mail.getSubject(), mail.getText());
					
			        /*
					mail.setSubject(contact.getSujet());
					mail.setText(contact.getMessage());
					//envoi de mail vers l'équipe aprés vente
					mailService.sendSslMessage(mail.getTo(), mail.getSubject(),mail.getText());
										*/
				}
			}
			
			userRepository.save(userEntity);
			return dozerBeanMapper.map(userEntity, User.class);
		} else {
			return null;
		}
	}

	public User findUserByMailAndPass(String mail, String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
		List<UsersEntity> users = userRepository.findByEmail(mail);
		if (users.isEmpty()) {
			return null;
		}
		
		
		return crypter.validatePassword(pass, users.get(0).getPassword())?dozerBeanMapper.map(users.get(0), User.class):null;

	}

	@Override
	public User changePassword(String idUser, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		List<UsersEntity> usersE = userRepository.findByIdUsers(idUser);
		UsersEntity userEntity = CollectionUtils.isEmpty(usersE) ? null : usersE.get(0);
		if (userEntity != null) {
			userEntity.setPassword(crypter.generateStorngPasswordHash(password));
			// dozerBeanMapper.map(user, userToUpdate);
			if(!userEntity.isActif()){
				userEntity.setActif(true);
			}
			userRepository.save(userEntity);
			return dozerBeanMapper.map(userEntity, User.class);
		} else {
			return null;
		}

	}
		/*
		User user = userService.findUserByUserId(userToChange.getIdUsers());
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		user.setPassword(userToChange.getPassword());
		User userToUpdate = userService.updateUser(user, user.get);
		*/
	

	@Override
	public User findUserByMail(String mail) {
		List<UsersEntity> users = userRepository.findByEmail(mail);

		if (users.isEmpty()) {
			return null;
		}
		return dozerBeanMapper.map(users.get(0), User.class);

	}
	
	
	   

}