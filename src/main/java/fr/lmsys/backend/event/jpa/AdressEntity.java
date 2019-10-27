package fr.lmsys.backend.event.jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Table(name="ADRESSE")
//@Entity
public class AdressEntity implements Serializable{
	private static final long serialVersionUID = -6689667948783341773L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_ADRESS")
	private long idAdress;

	private String adresse;
	private String zipCode;
	private String pays;
	
	public long getIdAdress() {
		return idAdress;
	}
	public void setIdAdress(long idAdress) {
		this.idAdress = idAdress;
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	@Override
	public String toString() {
		return "AdressEntity [idAdress=" + idAdress + ", adresse=" + adresse + ", zipCode="
				+ zipCode + ", pays=" + pays + "]";
	}
	
	
	

}
