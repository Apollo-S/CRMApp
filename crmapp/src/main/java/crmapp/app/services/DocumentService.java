package crmapp.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crmapp.app.controllers.DocumentController;
import crmapp.app.repositories.DocumentRepository;

@Service
public class DocumentService implements BaseService {

	private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);
	
	@Autowired
	private DocumentRepository repository;
	
}
