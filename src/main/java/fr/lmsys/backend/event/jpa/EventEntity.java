package fr.lmsys.backend.event.jpa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="EVENT")
public class EventEntity implements Serializable{
	private static final long serialVersionUID = -6051282044219960235L;
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String idEvent;
	
	private String titleEvent;
	@Lob 
	@Column(name="DESC_EVENT", length=10000)
	private String descEvent;
	private String adressEvent;
	private LocalDate startEvent;
	private LocalDate endEvent;
	private String organisation;
	@Lob 
	@Column(name="DESC_ORGANISATION", length=5000)
	private String descOrganisation;
	
	private int price;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "ID_USER_EVENT", referencedColumnName = "idusers")
	private UsersEntity userEvent;
	
	@OneToMany(mappedBy="imagesEvent",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<DocumentMetadataEntity> images;// = new HashSet<DocumentMetadataEntity>();
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TYPE_EVENEMENT")
	private TypeEvenementEntity typeEvent;
	
	public UsersEntity getUserEvent() {
		return userEvent;
	}
	public void setUserEvent(UsersEntity userEvent) {
		this.userEvent = userEvent;
	}
	public String getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}
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
	/*public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	*/
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void attachToUser(UsersEntity user) {
		user.addEventsRealise(this);
	}
	
	public Set<DocumentMetadataEntity> getImages() {
		return images;
	}
	public void setImages(Set<DocumentMetadataEntity> images) {
		this.images = images;
	}
	
	
	public void addImages(DocumentMetadataEntity e) {
		this.images.add(e);
		e.setImagesEvent(this);
	}
	
	
	@Override
	public String toString() {
		return "EventEntity [idEvent=" + idEvent + ", titleEvent=" + titleEvent + ", descEvent=" + descEvent
				+ ", adressEvent=" + adressEvent + ", startEvent=" + startEvent + ", endEvent=" + endEvent
				+ ", organisation=" + organisation + ", desOrganisation=" + descOrganisation // + ", urlImage=" + urlImage
				+ ", price=" + price + "typeEvent=" + typeEvent+"]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public TypeEvenementEntity getTypeEvent() {
		return typeEvent;
	}
	public void setTypeEvent(TypeEvenementEntity typeEvent) {
		this.typeEvent = typeEvent;
	}
	
	
}
