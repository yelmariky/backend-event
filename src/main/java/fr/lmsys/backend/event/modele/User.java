package fr.lmsys.backend.event.modele;

import java.util.Set;

public class User {
	private String idUsers;
	private String lastname;
	private String firstname;
	private boolean actif;
	private String email;

	//private Adress adress;

	private String phone;

	private String password;
	private Set<Contact> contacts;
	private Set<Event> eventsRealises;

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}
*/
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Event> getEventsRealises() {
		return eventsRealises;
	}

	public void setEventsRealises(Set<Event> eventsRealises) {
		this.eventsRealises = eventsRealises;
	}

	public String getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(String idusers) {
		this.idUsers = idusers;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

}
