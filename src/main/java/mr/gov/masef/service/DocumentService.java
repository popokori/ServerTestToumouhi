package mr.gov.masef.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mr.gov.masef.entites.Document;
import mr.gov.masef.repository.DocumentRepository;
@Service
public class DocumentService {
	@Autowired
	DocumentRepository documentRepository;
	
	public Document creatDoc(Document doc) {
		return documentRepository.save(doc);
		
	}
	
	

}
