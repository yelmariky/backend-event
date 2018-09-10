package fr.lmsys.backend.event.repositories;

import org.springframework.data.repository.CrudRepository;

import fr.lmsys.backend.event.jpa.UsersEntity;
import java.lang.String;
import java.util.List;

public interface UserRepository extends CrudRepository<UsersEntity, Long> {
	List<UsersEntity> findByIdUsers(String idUsers);
	List<UsersEntity> findByEmail(String email);
	List<UsersEntity> findByEmailAndPassword(String email,String password);
	
}
