package fr.lmsys.backend.event.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import fr.lmsys.backend.event.modele.User;

public interface UserService {
	
	User saveUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException ;

	public List<User> getAllUsers();

	public User findUser( String id);

	public User updateUser(User user, boolean isMail) throws AddressException, MessagingException, UnsupportedEncodingException;
	
	public User findUserByMailAndPass(String mail, String pass) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	public User findUserByMail(String mail);
	
	public User changePassword(String idUser, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

	public User activateUser(String idUser);
}