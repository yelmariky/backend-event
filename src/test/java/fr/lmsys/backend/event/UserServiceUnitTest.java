package fr.lmsys.backend.event;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import fr.lmsys.backend.event.jpa.UsersEntity;
import fr.lmsys.backend.event.modele.User;
import fr.lmsys.backend.event.repositories.ContactRepository;
import fr.lmsys.backend.event.repositories.UserRepository;
import fr.lmsys.backend.event.service.impl.CryptSh;
import fr.lmsys.backend.event.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceUnitTest {
	
	@Mock
	private UserRepository userRepository;
	@Mock
	private ContactRepository contactRepo;
	@Mock
	private CryptSh crypt;
	@InjectMocks
	private UserServiceImpl userService;
	

	User user;

	final DozerBeanMapper dozer = new DozerBeanMapper();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		
        userService.setDoze(dozer);
        
		user = new User();
		user.setEmail("email-event@lmsys.com");
		user.setFirstname("yanick");
		user.setLastname("lebreton");
		user.setPassword("yanick90");
		user.setPhone("0664874512");
	}
	
	/**
	 * dozerBeanMapper.map(user, UsersEntity.class);
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	@Test
	public void saveUser_givenuser_when_userOK_then_usersaved() throws NoSuchAlgorithmException, InvalidKeySpecException{
		UsersEntity ujpa=dozer.map(user, UsersEntity.class);
	
		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
		Mockito.when(userRepository.save(ujpa)).thenReturn(ujpa);
		//userService=new UserServiceImpl(userRepository,contactRepo);
		userService.saveUser(user);
		
		 Mockito.verify(userRepository, Mockito.times(1)).save(ujpa);
	}
	
	@Test
	public void saveUser_givenuserwithoutmail_when_userOK_then_usernotsaved() throws NoSuchAlgorithmException, InvalidKeySpecException{
		user.setEmail(null);
		UsersEntity ujpa=dozer.map(user, UsersEntity.class);
	
		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
		Mockito.when(userRepository.save(ujpa)).thenReturn(ujpa);
		userService.saveUser(user);
		
		 Mockito.verify(userRepository, Mockito.times(1)).save(ujpa);
	}
	@Test
	public void saveUser_givenExistUser_when_userOK_then_usernotsaved() throws NoSuchAlgorithmException, InvalidKeySpecException{
		
		UsersEntity ujpa=dozer.map(user, UsersEntity.class);
	
		Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Arrays.asList(ujpa));
		Mockito.when(userRepository.save(ujpa)).thenReturn(ujpa);
		User usaved=userService.saveUser(user);
		
		Assert.assertNull(usaved);

	}
}
