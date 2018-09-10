package fr.lmsys.backend.event.modele;

public class TypeEvenement {
	private String typeEvenement;
	private int idTypeEvent;

	public TypeEvenement(String typeEvenement,int idTypeEvent) {
		super();
		this.setTypeEvenement(typeEvenement);
		this.setIdTypeEvent(idTypeEvent);
	}

	public TypeEvenement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTypeEvenement() {
		return typeEvenement;
	}

	public void setTypeEvenement(String typeEvenement) {
		this.typeEvenement = typeEvenement;
	}

	public int getIdTypeEvent() {
		return idTypeEvent;
	}

	public void setIdTypeEvent(int idTypeEvent) {
		this.idTypeEvent = idTypeEvent;
	}

}
