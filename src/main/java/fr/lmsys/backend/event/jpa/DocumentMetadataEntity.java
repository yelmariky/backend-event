package fr.lmsys.backend.event.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Table(name="Document_Metadata")
@Entity
public class DocumentMetadataEntity implements Serializable {
	private static final long serialVersionUID = 4989998866593325241L;

    
    public static final String PROP_UUID = "uuid";
    public static final String PROP_PERSON_NAME = "person-name";
    public static final String PROP_FILE_NAME = "file-name";
    public static final String PROP_DOCUMENT_DATE = "document-date";
    
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
    @Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idDocument;
    private String idUser;
    protected String uuid;
    protected String fileName;
    protected LocalDateTime documentDate;
    private String urlImage;
   
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "image_event", referencedColumnName = "idEvent")
	private EventEntity imagesEvent;
    
   
    
    public DocumentMetadataEntity() {
        super();
    }

    public DocumentMetadataEntity(String fileName, LocalDateTime documentDate) {
        this(UUID.randomUUID().toString(), fileName, documentDate);
    }
    
    public DocumentMetadataEntity(String uuid, String fileName, LocalDateTime documentDate) {
        super();
        this.uuid = uuid;
        this.fileName = fileName;
        this.documentDate = documentDate;
       
    }
    
    public DocumentMetadataEntity(Properties properties) {
        this(properties.getProperty(PROP_UUID),
             properties.getProperty(PROP_FILE_NAME),
             null);
        String dateString = properties.getProperty(PROP_DOCUMENT_DATE);
        if(dateString!=null) {
            this.documentDate =LocalDateTime.parse(dateString, DATE_FORMAT);//.parse(dateString).getTime());
        }    
    }

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public LocalDateTime getDocumentDate() {
        return documentDate;
    }
    public void setDocumentDate(LocalDateTime documentDate) {
        this.documentDate = documentDate;
    }
    
   public EventEntity getImagesEvent() {
		return imagesEvent;
	}

	public void setImagesEvent(EventEntity imagesEvent) {
		this.imagesEvent = imagesEvent;
	}
	
	public void attachToEvent(EventEntity event) {
		event.addImages(this);
	}

	public Properties createProperties() {
        Properties props = new Properties();
        props.setProperty(PROP_UUID, getUuid());
        props.setProperty(PROP_FILE_NAME, getFileName());
       
        props.setProperty(PROP_DOCUMENT_DATE, DATE_FORMAT.format(getDocumentDate()));
        return props;
    }

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
    
    
    
    
}
