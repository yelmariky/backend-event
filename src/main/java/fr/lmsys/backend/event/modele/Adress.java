package fr.lmsys.backend.event.modele;

public class Adress {
	private String adresse;
	private String zipCode;
	private String pays;

	

	public Adress(String adresse, String zipCode, String pays) {
		super();
		this.adresse = adresse;
		this.zipCode = zipCode;
		this.pays = pays;
	}
	
	

	public Adress() {
		super();
		// TODO Auto-generated constructor stub
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

}
