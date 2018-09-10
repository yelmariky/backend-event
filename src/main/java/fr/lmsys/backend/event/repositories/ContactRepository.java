package fr.lmsys.backend.event.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.lmsys.backend.event.jpa.ContactEntity;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity, Long>{

	
}
