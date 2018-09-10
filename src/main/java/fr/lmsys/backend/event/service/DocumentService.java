package fr.lmsys.backend.event.service;

import java.util.List;

import fr.lmsys.backend.event.modele.DocumentMetadata;

public interface DocumentService {

	public void saveDocument(DocumentMetadata event);

	public DocumentMetadata updateEvent(DocumentMetadata document, Long id);
	
	public List<DocumentMetadata> getDocuments();

}