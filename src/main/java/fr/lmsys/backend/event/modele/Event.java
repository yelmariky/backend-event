package fr.lmsys.backend.event.modele;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Event {
	private String idEvent;
	private String titleEvent;
	private String descEvent;
	private String adressEvent;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate startEvent;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate endEvent;
	private String organisation;
	private String descOrganisation;
	private Set<DocumentMetadata> images;
	
	private int price;
	
	private User userEvent;
	private TypeEvenement typeEvent;

	public String getTitleEvent() {
		return titleEvent;
	}

	public void setTitleEvent(String titleEvent) {
		this.titleEvent = titleEvent;
	}

	public String getDescEvent() {
		return descEvent;
	}

	public void setDescEvent(String descEvent) {
		this.descEvent = descEvent;
	}

	public String getAdressEvent() {
		return adressEvent;
	}

	public void setAdressEvent(String adressEvent) {
		this.adressEvent = adressEvent;
	}

	public LocalDate getStartEvent() {
		return startEvent;
	}

	public void setStartEvent(LocalDate startEvent) {
		this.startEvent = startEvent;
	}

	public LocalDate getEndEvent() {
		return endEvent;
	}

	public void setEndEvent(LocalDate endEvent) {
		this.endEvent = endEvent;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getDescOrganisation() {
		return descOrganisation;
	}

	public void setDescOrganisation(String desOrganisation) {
		this.descOrganisation = desOrganisation;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public User getUserEvent() {
		return userEvent;
	}

	public void setUserEvent(User userEvent) {
		this.userEvent = userEvent;
	}

	public Set<DocumentMetadata> getImages() {
		return images;
	}

	public void setImages(Set<DocumentMetadata> images) {
		this.images = images;
	}

	public String getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}

	public TypeEvenement getTypeEvent() {
		return typeEvent;
	}

	public void setTypeEvent(TypeEvenement typeEvenement) {
		this.typeEvent = typeEvenement;
	}

	
}
