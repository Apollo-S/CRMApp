package crmapp.app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crmapp.app.entities.Document;
import crmapp.app.repositories.DocumentRepository;

@Service
public class DocumentService implements BaseService<Document> {

	private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);
	
	@Autowired
	private DocumentRepository repository;
	
	@Override
	public List<Document> getAll() {
		logger.info("OK: DocumentService.getAll()");
		List<Document> documents = repository.findAll();
		logger.info("OK: Documents in quantity="+ documents.size() + " found");
		return documents;
	}

	@Override
	public Document getById(int id) {
		logger.info("OK: DocumentService.getById()");
		Document document = repository.findOne(id);
		logger.info("OK: Document with ID="+ id + " found");
		return document;
	}

	@Override
	public Document save(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

}
