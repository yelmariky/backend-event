package fr.lmsys.backend.event.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.lmsys.backend.event.jpa.EventEntity;
import java.lang.String;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<EventEntity, Long>{
	List<EventEntity> findByIdEvent(String idevent);
	
}
