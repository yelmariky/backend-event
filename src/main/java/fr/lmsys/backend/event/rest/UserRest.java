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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.lmsys.backend.event.modele.Mail;
import fr.lmsys.backend.event.modele.User;
import fr.lmsys.backend.event.service.UserService;
import fr.lmsys.backend.event.service.impl.MailService;

@RestController
//@CrossOrigin
@RequestMapping("/api/users")
public class UserRest {

	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;
	
	@Value("${server.frontend}")
	private String urlfrontend;
	@Value("${mail.from}")
	String from;

	@PostMapping
	public User saveUser(@RequestBody User user) throws Exception {
		User userRes= userService.saveUser(user);
		
		//pas d'envoi de mail si existe
		if(userRes !=null){
		//envoi de mail de confirmation
		Mail mail = new Mail();  
		mail.setTo(user.getEmail());
		
		mail.setSubject("création de compte");
		mail.setText("Nous avons le plaisir de vous inscrire sur notre site internet. \n merci de vous connecter via ce lien "
				+ urlfrontend + "/acceuil/"+user.getIdUsers()+"\n Cordialement. ");
		
		mailService.sendMail(from, mail.getTo(), mail.getSubject(),mail.getText());
		}
		return userRes;
	}

	@PostMapping(value = "/_update/pass")
	public ResponseEntity<User> changePassword(@RequestBody User userToChange) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = userService.changePassword(userToChange.getIdUsers(), userToChange.getPassword());
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping
	@PreAuthorize("#oauth2.hasAnyScope('phone','test01','openid')")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping(value = "/{id}")
	public User findUser(@PathVariable(value = "id") String idUser) {
		return userService.findUser(idUser);
	}
	
	@PostMapping(value = "/_find")
	public ResponseEntity<User> findUserByPost(@RequestBody User userToFind) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = userService.findUserByMailAndPass(userToFind.getEmail(), userToFind.getPassword());
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping(value = "/_send")
	public User changePasswordByMail(@RequestParam(value = "mail") String mail) throws Exception {
		User user = userService.findUserByMail(mail);
		if (user == null) {
			throw new Exception("mail not found");
		}
		mailService.sendMail(from,mail, "Modification du mot de pass", "Veuillez modifier votre mot de pass \n cliquer sur "
				+ "le lien suivant "+ urlfrontend + "/changepass/" + user.getIdUsers() + "\n Cordialement");
		return user;
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser( @RequestBody User user,@RequestParam(value = "ismail") boolean isMail) throws AddressException, MessagingException, UnsupportedEncodingException {
		User userToUpdate = userService.updateUser(user,isMail);
		
		
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(userToUpdate, HttpStatus.OK);

	}
	@PostMapping(value = "/_activate")
	public ResponseEntity<User> activateUser( @RequestBody String idUser) {
		User userToUpdate = userService.activateUser(idUser);
		if (userToUpdate == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(userToUpdate, HttpStatus.OK);

	}
	
	@GetMapping(value = "/_nbevent/user/{id_user}")
	public ResponseEntity<Integer> calculNbEvent(@PathVariable(value = "id_user") String idUser) {
		User user = userService.findUser(idUser);
		if (user == null) {
			return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Integer>(user.getEventsRealises().size(), HttpStatus.OK);

	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> nfeHandler(Exception ex) {
		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}
