package fr.lmsys.backend.event.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.lmsys.backend.event.jpa.TypeEvenementEntity;

@Repository
public interface TypeEventRepository extends CrudRepository<TypeEvenementEntity, Long>{
	
	
}
