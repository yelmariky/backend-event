package fr.lmsys.backend.event.rest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.lmsys.backend.event.modele.Mail;
import fr.lmsys.backend.event.modele.User;
import fr.lmsys.backend.event.service.UserService;
import fr.lmsys.backend.event.service.impl.NotificationService;

@RestController
//@CrossOrigin
@RequestMapping("/api/user")
public class UserRest {

	@Autowired
	private UserService userService;
	@Autowired
	private NotificationService mailService;
	
	@Value("${server.frontend}")
	private String urlfrontend;
	@Value("${mail.from}")
	String from;

	@RequestMapping(value = "/_save", method = RequestMethod.PUT)
	public User saveUser(@RequestBody User user) throws Exception {
		User userRes= userService.saveUser(user);
		
		//pas d'envoi de mail si existe
		if(userRes !=null){
		//envoi de mail de confirmation
		Mail mail = new Mail();  
		mail.setTo(user.getEmail());
		
		mail.setSubject("création de compte");
		mail.setText("Nous avons le plaisir de vous inscrire sur notre site internet. \n merci de vous connecter via ce lien "
				+" https://" + urlfrontend + "/acceuil/"+user.getIdUsers()+"\n Cordialement. ");
		
		mailService.sendNotification(from, mail.getTo(), mail.getSubject(),mail.getText());
		}
		return userRes;
	}

	@RequestMapping(value = "/_update/pass", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestBody User userToChange) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = userService.changePassword(userToChange.getIdUsers(), userToChange.getPassword());
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/_getAll", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/_get/id/{id}", method = RequestMethod.GET)
	public User findUser(@PathVariable(value = "id") String idUser) {
		return userService.findUser(idUser);
	}
	
	//@CrossOrigin(origins={"http://lebonevenement.fr","http://localhost:4200"})
	@RequestMapping(value = "/_get", method = RequestMethod.GET)
	public ResponseEntity findUserByMailAndPass(@RequestParam(value = "mail") String mail,
			@RequestParam(value = "pass") String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		User user = userService.findUserByMailAndPass(mail, password);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/_send", method = RequestMethod.GET)
	public User changePasswordByMail(@RequestParam(value = "mail") String mail) throws Exception {
		User user = userService.findUserByMail(mail);
		if (user == null) {
			throw new Exception("mail not found");
		}
		mailService.sendNotification(from,mail, "Modification du mot de pass", "Veuillez modifier votre mot de pass \n cliquer sur "
				+ "le lien suivant https://" + urlfrontend + "/changepass/" + user.getIdUsers() + "\n Cordialement");
		return user;
	}
	
	@RequestMapping(value = "/_update", method = RequestMethod.POST)
	public ResponseEntity<?> updateUser( @RequestBody User user,@RequestParam(value = "ismail") boolean isMail) throws AddressException, MessagingException, UnsupportedEncodingException {
		User userToUpdate = userService.updateUser(user,isMail);
		
		
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(userToUpdate, HttpStatus.OK);

	}
	@RequestMapping(value = "/_activate", method = RequestMethod.POST)
	public ResponseEntity<?> activateUser( @RequestBody String idUser) {
		User userToUpdate = userService.activateUser(idUser);
		if (userToUpdate == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(userToUpdate, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/_nbevent/user/{id_user}", method = RequestMethod.GET)
	public ResponseEntity<Integer> calculNbEvent(@PathVariable(value = "id_user") String idUser) {
		User user = userService.findUser(idUser);
		if (user == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Integer>(user.getEventsRealises().size(), HttpStatus.OK);

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> nfeHandler(Exception ex) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}
