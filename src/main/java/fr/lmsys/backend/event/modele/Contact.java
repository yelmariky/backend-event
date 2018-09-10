package fr.lmsys.backend.event.modele;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Contact {

	private String idContact;

	private String sujet;

	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateReception;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateTraitement;
	
	

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

	

}
