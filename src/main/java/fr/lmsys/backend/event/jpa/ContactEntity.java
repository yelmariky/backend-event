package fr.lmsys.backend.event.jpa;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
public class ContactEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2807296083919952005L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String idContact;
	
	private String sujet;
	@Lob 
	@Column(name="message", length=10000)
	private String message;
	
	private LocalDate dateReception;
	private LocalDate dateTraitement;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_USER_CONTACT", referencedColumnName = "idusers")
	private UsersEntity userContact;
	
	public void attachToUser(UsersEntity user) {
		user.addContacts(this);
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}
	
	
	


	public String getIdContact() {
		return idContact;
	}



	public void setIdContact(String idContact) {
		this.idContact = idContact;
	}



	public String getSujet() {
		return sujet;
	}



	public void setSujet(String sujet) {
		this.sujet = sujet;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public LocalDate getDateReception() {
		return dateReception;
	}



	public void setDateReception(LocalDate dateReception) {
		this.dateReception = dateReception;
	}



	public LocalDate getDateTraitement() {
		return dateTraitement;
	}



	public void setDateTraitement(LocalDate dateTraitement) {
		this.dateTraitement = dateTraitement;
	}



	public UsersEntity getUserContact() {
		return userContact;
	}



	public void setUserContact(UsersEntity userContact) {
		this.userContact = userContact;
	}



	@Override
	public String toString() {
		return "ContactEntity [idContact=" + idContact + ", sujet=" + sujet + ", message=" + message
				+ ", dateReception=" + dateReception + ", dateTraitement=" + dateTraitement + ", userContact="
				+ userContact + "]";
	}

}
