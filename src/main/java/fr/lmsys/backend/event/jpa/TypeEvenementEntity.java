package fr.lmsys.backend.event.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "type_evenement")
@Entity
public class TypeEvenementEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5891741256947891241L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TYPE_EVENEMENT")
	private long idTypeEvent;

	private String typeEvenement;

	public TypeEvenementEntity(String typevent) {
		this.typeEvenement = typevent;
	}
	
	

	public TypeEvenementEntity(long idTypeEvent, String typeEvenement) {
		super();
		this.idTypeEvent = idTypeEvent;
		this.typeEvenement = typeEvenement;
	}



	/**
	 * private String typeEvenement; private int idTypeEvent;
	 * 
	 * @param typeEvent
	 */

	public TypeEvenementEntity() {
		super();
	}

	public long getIdTypeEvent() {
		return idTypeEvent;
	}

	public void setIdTypeEvent(long idTypeEvent) {
		this.idTypeEvent = idTypeEvent;
	}

	public String getTypeEvenement() {
		return typeEvenement;
	}

	public void setTypeEvenement(String typeEvenement) {
		this.typeEvenement = typeEvenement;
	}

}
