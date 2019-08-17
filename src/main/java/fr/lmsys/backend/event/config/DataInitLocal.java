package fr.lmsys.backend.event.config;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.lmsys.backend.event.jpa.TypeEvenementEntity;
import fr.lmsys.backend.event.repositories.TypeEventRepository;

@Configuration
@Profile("local")
public class DataInitLocal {
	
@Autowired
private TypeEventRepository typeEventRepo;
	
	@PostConstruct
	public void loadIfInMemory() throws Exception {
		typeEventRepo.save(new TypeEvenementEntity("Attraction"));
		typeEventRepo.save(new TypeEvenementEntity("Salon professionel ou Exposition"));
		
		typeEventRepo.save(new TypeEvenementEntity("Formation ou Atelier"));
		typeEventRepo.save(new TypeEvenementEntity("Sportif"));
		typeEventRepo.save(new TypeEvenementEntity("Conférence ou Séminaire"));
		typeEventRepo.save(new TypeEvenementEntity("Dîner ou Gala"));
	
	}

}
