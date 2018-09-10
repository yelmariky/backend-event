package fr.lmsys.backend.event.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="USER_EVENT")
@Entity
public class UserEventEntity implements Serializable{

	private static final long serialVersionUID = -694599870002285531L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "ID_USER", nullable = false)
	private long idUser;
	
	
	private long idEvents;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUsers) {
		this.idUser = idUsers;
	}
	public long getIdEvents() {
		return idEvents;
	}
	public void setIdEvents(long idEvents) {
		this.idEvents = idEvents;
	}
	
	

}
