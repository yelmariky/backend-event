package fr.lmsys.backend.event.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import fr.lmsys.backend.event.dao.IDocumentDao;
import fr.lmsys.backend.event.modele.Document;
import fr.lmsys.backend.event.modele.DocumentMetadata;

@Service("archiveService")
public class ArchiveService implements IArchiveService, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6597482063803195218L;
	@Autowired
    private IDocumentDao DocumentDao;

    /**
     * Saves a document in the archive.
     * @see fr.lmsys.backend.event.service.IArchiveService#save(fr.lmsys.backend.event.service.Document)
     */
    @Override
    public DocumentMetadata save(Document document) {
        getDocumentDao().insert(document); 
        return document.getMetadata();
    }
    
    /**
     * Finds document in the archive
     * @see fr.lmsys.backend.event.service.IArchiveService#findDocuments(java.lang.String, java.util.Date)
     */
    @Override
	public List<DocumentMetadata> findDocuments(String personName, Date date) {
        return getDocumentDao().findByPersonNameDate(personName, date);
    }
    
    /**
     * Returns the document file from the archive
     * @see fr.lmsys.backend.event.service.IArchiveService#getDocumentFile(java.lang.String)
     */
    @Override
    public byte[] getDocumentFile(String id) {
        Document document = getDocumentDao().load(id);
        if(document!=null) {
            return document.getFileData();
        } else {
            return null;
        }
    }


    public IDocumentDao getDocumentDao() {
        return DocumentDao;
    }

    public void setDocumentDao(IDocumentDao documentDao) {
        DocumentDao = documentDao;
    }

	@Override
	public List<DocumentMetadata> findDocumentsByPersonne(String personName) {
		return getDocumentDao().findByPersonName(personName);
	}

	@Override
	public Resource loadFile(String filename) {
		return getDocumentDao().loadFile(filename);
	}


}
