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

import crmapp.app.entities.DocumentStatus;
import crmapp.app.repositories.DocumentStatusRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/document-statuses")
public class DocumentStatusController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(DocumentStatusController.class);
	
	@Autowired
	private DocumentStatusRepository docStatusRepository;
	
	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<DocumentStatus>> getAllDocumentStatuses() {
		List<DocumentStatus> docStatuses = docStatusRepository.findAll();
		if (docStatuses.size() == 0) {
			logger.info("<==/////////// There are no any docStatuses ... ///////////==>");
			return new ResponseEntity<List<DocumentStatus>>(HttpStatus.NO_CONTENT);
		}
		logger.info("<==/////////// Printing docStatuses: " + docStatuses + " ///////////==>");
		return new ResponseEntity<List<DocumentStatus>>(docStatuses, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<DocumentStatus> getDocumentStatusById(@PathVariable(PARAM_ID) int id) {
		DocumentStatus docType = docStatusRepository.findOne(id);
		if (docType == null) {
			logger.info("<==/////////// docStatus with ID=" + id + " not found! ///////////==>");
			return new ResponseEntity<DocumentStatus>(docType, HttpStatus.NOT_FOUND);
		}
		logger.info("<==/////////// docStatus with ID=" + id + " found! ///////////==>");
		return new ResponseEntity<DocumentStatus>(docType, HttpStatus.OK);
	}
	
}
