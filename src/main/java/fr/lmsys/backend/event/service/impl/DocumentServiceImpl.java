package fr.lmsys.backend.event.service.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.lmsys.backend.event.modele.DocumentMetadata;
import fr.lmsys.backend.event.repositories.DocumentRepository;
import fr.lmsys.backend.event.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	DozerBeanMapper dozerBeanMapper;
  
	@Override
	public void saveDocument(DocumentMetadata event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DocumentMetadata updateEvent(DocumentMetadata document, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentMetadata> getDocuments() {
		// TODO Auto-generated method stub
		return null;
	}

	   
    
}