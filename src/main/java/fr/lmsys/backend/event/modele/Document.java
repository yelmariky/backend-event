package fr.lmsys.backend.event.modele;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * 
 * @author 
 *
 */
public class Document extends DocumentMetadata implements Serializable {

    private static final long serialVersionUID = 2004955454853853315L;
    
    private byte[] fileData;
    
    public Document( byte[] fileData, String fileName, LocalDateTime documentDate, String idUser) {
        super(fileName, documentDate, idUser);
        this.fileData = fileData;
    }

    public Document(Properties properties) {
        super(properties);
    }
    
    public Document(DocumentMetadata metadata) {
        super(metadata.getUuid(), metadata.getFileName(), metadata.getDocumentDate(), metadata.getIdUser());
    }

    public byte[] getFileData() {
        return fileData;
    }
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
    
    public DocumentMetadata getMetadata() {
        return new DocumentMetadata(getUuid(), getFileName(), getDocumentDate(), getIdUser());
    }
    
}
