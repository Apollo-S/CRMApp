package crmapp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.ClientDirector;
import crmapp.app.entities.DocumentType;
import crmapp.app.repositories.DocumentTypeRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/document-types")
public class DocumentTypeController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentTypeController.class);
	
	@Autowired
	private DocumentTypeRepository docTypeRepository;
	
	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<DocumentType>> getAllDocumentTypes() {
		List<DocumentType> docTypes = docTypeRepository.findAll();
		if (docTypes.size() == 0) {
			logger.info("<==/////////// There are no any docType... ///////////==>");
			return new ResponseEntity<List<DocumentType>>(HttpStatus.NO_CONTENT);
		}
		logger.info("<==/////////// Printing docTypes: " + docTypes + " ///////////==>");
		return new ResponseEntity<List<DocumentType>>(docTypes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<DocumentType> getDocumentTypeById(@PathVariable(PARAM_ID) int id) {
		DocumentType docType = docTypeRepository.findOne(id);
		if (docType == null) {
			logger.info("<==/////////// docType with ID=" + id + " not found! ///////////==>");
			return new ResponseEntity<DocumentType>(docType, HttpStatus.NOT_FOUND);
		}
		logger.info("<==/////////// docType with ID=" + id + " found! ///////////==>");
		return new ResponseEntity<DocumentType>(docType, HttpStatus.OK);
	}
	
}
