package fr.lmsys.backend.event.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class UsersEntity implements Serializable {

	private static final long serialVersionUID = 5698388545338241703L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	//@Column(name="ID_USERS",unique=true)
	private String idUsers;
	
	private String lastname;
	private String firstname;
	//@Column(name="email"  , nullable=false , unique=true)
	private String email;
	
	private boolean actif=false; 
	
	/*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional=false)
	@JoinColumn(name = "ID_ADRESS", referencedColumnName = "ID_ADRESS", nullable = false, unique = false, insertable = true, updatable = true)
	private AdressEntity adress;
	*/
	@OneToMany(mappedBy="userEvent",fetch=FetchType.EAGER)
	private Set<EventEntity> eventsRealises = new HashSet<EventEntity>();
	
	@OneToMany(mappedBy="userContact",fetch=FetchType.LAZY)
	private Set<ContactEntity> contacts = new HashSet<ContactEntity>();
	
	private String phone;
	
	@Column(name="PASSWORD", length=256)
	private String password;
	
	
	public String getIdUsers() {
		return idUsers;
	}
	public void setIdUsers(String idusers) {
		this.idUsers = idusers;
	}
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	/*public AdressEntity getAdress() {
		return adress;
	}
	public void setAdress(AdressEntity adress) {
		this.adress = adress;
	}*/
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
	public Set<EventEntity> getEventsRealises() {
		return eventsRealises;
	}
	public void setEventsRealises(Set<EventEntity> eventsRealises) {
		this.eventsRealises = eventsRealises;
	}
	
	
	public void addEventsRealise(EventEntity e) {
		this.eventsRealises.add(e);
		e.setUserEvent(this);
	}
	
	public void addContacts(ContactEntity e) {
		this.contacts.add(e);
		e.setUserContact(this);
	}
	
	@Override
	public String toString() {
		return "UsersEntity [idusers=" + idUsers + ", lastname=" + lastname + ", firstname=" + firstname + ", email="
				+ email + ", phone=" + phone + ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersEntity other = (UsersEntity) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	public Set<ContactEntity> getContacts() {
		return contacts;
	}
	public void setContacts(Set<ContactEntity> contacts) {
		this.contacts = contacts;
	}
	
	
	
	
	

}
