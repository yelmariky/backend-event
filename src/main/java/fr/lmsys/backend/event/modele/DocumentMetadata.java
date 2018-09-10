package fr.lmsys.backend.event.modele;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DocumentMetadata implements Serializable {
    
    static final long serialVersionUID = 7283287076019483950L;

    
    public static final String PROP_UUID = "uuid";
    public static final String PROP_PERSON_ID = "person-id";
    public static final String PROP_FILE_NAME = "file-name";
    public static final String PROP_DOCUMENT_DATE = "document-date";
    
   // public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    //public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected String uuid;
    protected String fileName;
    private String urlImage;
   
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime documentDate;
    private String idUser;
    
    public DocumentMetadata() {
        super();
    }

    public DocumentMetadata(String fileName, LocalDateTime documentDate, String idUser) {
        this(UUID.randomUUID().toString(), fileName, documentDate,idUser);
    }
    
    public DocumentMetadata(String uuid, String fileName, LocalDateTime documentDate, String idUser) {
        super();
        this.uuid = uuid;
        this.fileName = fileName;
        this.documentDate = documentDate;
        this.setIdUser(idUser);
    }
    
    public DocumentMetadata(Properties properties) {
        this(properties.getProperty(PROP_UUID),
             properties.getProperty(PROP_FILE_NAME),
             null,
             properties.getProperty(PROP_PERSON_ID));
        String dateString = properties.getProperty(PROP_DOCUMENT_DATE);
        if(dateString!=null) {
          
               this.documentDate =  LocalDateTime.parse(dateString, formatter);// LocalDate.DATE_FORMAT.parse(dateString);
               
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
    
    public String getUrlImage(){
    	return urlImage;
    }
   /* 
    public String getUrlImage(){
    	return "http://localhost:8081/event-backend/api/upload/user/"+idUser+"/files/"+getFileName();
    }
    */
    public LocalDateTime getDocumentDate() {
        return documentDate;
    }
    public void setDocumentDate(LocalDateTime documentDate) {
        this.documentDate = documentDate;
    }
    
   
    
    public Properties createProperties() {
        Properties props = new Properties();
        props.setProperty(PROP_UUID, getUuid());
        props.setProperty(PROP_FILE_NAME, getFileName());
        props.setProperty(PROP_PERSON_ID, String.valueOf(getIdUser()));
        props.setProperty(PROP_DOCUMENT_DATE,LocalDateTime.now().format(formatter));// DATE_FORMAT.format(getDocumentDate()));
        return props;
    }

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
    public static void main(String[] args) {
    	String str = "1986-04-08 12:30:50";
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
    	
    	System.out.println("DATE: "+dateTime.getSecond());
	}
    
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
    
}
