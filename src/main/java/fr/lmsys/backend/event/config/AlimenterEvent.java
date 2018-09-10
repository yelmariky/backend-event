package fr.lmsys.backend.event.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.lmsys.backend.event.repositories.TypeEventRepository;

@Component
public class AlimenterEvent implements CommandLineRunner {
	@Autowired
	private TypeEventRepository typeEventRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
	/*	typeEventRepo.save(new TypeEvenementEntity("Attraction"));
		typeEventRepo.save(new TypeEvenementEntity("Salon professionel ou Exposition"));
		
		typeEventRepo.save(new TypeEvenementEntity("Formation ou Atelier"));
		typeEventRepo.save(new TypeEvenementEntity("Sportif"));
		typeEventRepo.save(new TypeEvenementEntity("Conférence ou Séminaire"));
		typeEventRepo.save(new TypeEvenementEntity("Dîner ou Gala"));
		*/
//		//user1
//		User u = new User();
//	//	u.setAdress(new Adress("adresse01","94260","FRANCE"));
//		u.setFirstname("firstname01");
//		u.setLastname("lastname01");
//		u.setPhone("0624654752");
//		u.setPassword("password01");
//		u.setEmail("emai00@email00.fr");
//		
//		userService.saveUser(u);
//		
//		//Set<Event> setEvent=new HashSet<Event>();
//		
//		Event event = new Event();
//		event.setAdressEvent("adresseEvent01");
//		event.setDescEvent("desc event");
//		event.setDescOrganisation("desc organisation01");
//		event.setOrganisation("organisation01");
//		event.setPrice(90);
//		event.setTitleEvent("titleEvent01");
//		event.setStartEvent(LocalDate.now());
//		event.setEndEvent(LocalDate.now().plusDays(5));
//	//	event.setUrlImage("assets/images/fjords.jpg");
//		event.setUserEvent(u);
//		eventService.saveEvent(event);
//	//	setEvent.add(event);
//		
//		event = new Event();
//		event.setAdressEvent("adresseEvent02");
//		event.setDescEvent("desc event");
//		event.setDescOrganisation("desc organisation02");
//		event.setOrganisation("organisation02");
//		event.setPrice(10);
//		event.setTitleEvent("titleEvent02");
//		event.setStartEvent(LocalDate.now());
//		event.setEndEvent(LocalDate.now().plusDays(3));
//	//	event.setUrlImage("assets/images/nature.jpg");
//		event.setUserEvent(u);
//		eventService.saveEvent(event);
//		
//		//user2
//		u = new User();
//	//	u.setAdress(new Adress("adresse21","94260","FRANCE"));
//		u.setFirstname("firstname01");
//		u.setLastname("lastname01");
//		u.setPhone("0624654752");
//		u.setPassword("password02");
//		u.setEmail("emai01@email01.fr");
//		userService.saveUser(u);
//		
//		event = new Event();
//		event.setAdressEvent("adresseEvent03");
//		event.setDescEvent("desc event03");
//		event.setDescOrganisation("desc organisation03");
//		event.setOrganisation("organisation03");
//		event.setPrice(100);
//		event.setTitleEvent("titleEvent03");
//		event.setStartEvent(LocalDate.now());
//		event.setEndEvent(LocalDate.now().plusDays(5));
//	//	event.setUrlImage("assets/images/fjords.jpg");
//		event.setUserEvent(u);
//		eventService.saveEvent(event);
//		
//		event = new Event();
//		event.setAdressEvent("adresseEvent04");
//		event.setDescEvent("desc event04");
//		event.setDescOrganisation("desc organisation04");
//		event.setOrganisation("organisation04");
//		event.setPrice(150);
//		event.setTitleEvent("titleEvent04");
//		event.setStartEvent(LocalDate.now());
//		event.setEndEvent(LocalDate.now().plusDays(5));
//		//event.setUrlImage("assets/images/lights.jpg");
//		event.setUserEvent(u);
//		eventService.saveEvent(event);
//		
//		//user3 without event
//		u = new User();
//	//	u.setAdress(new Adress("adresse03","94260","FRANCE"));
//		u.setFirstname("firstname03");
//		u.setLastname("lastname03");
//		u.setPhone("0624654752");
//		u.setPassword("password03");
//		u.setEmail("emai03@email03.fr");
//		userService.saveUser(u);
		
		//upload files
	//	storageService.deleteAll();
//		storageService.init();
	

	}

}
