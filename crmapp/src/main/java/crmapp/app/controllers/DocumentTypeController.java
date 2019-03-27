package crmapp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.DocumentType;
import crmapp.app.services.DocumentTypeService;
import org.thymeleaf.dom.DocType;

@RestController
@RequestMapping(value = "/api/document-types")
public class DocumentTypeController extends BaseController<DocumentType, DocumentTypeService> {

	private static final Logger logger = LoggerFactory.getLogger(DocumentTypeController.class);

	@Autowired
	private DocumentTypeService service;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<DocumentType>> getAllDocumentTypes() {
		logger.info(LOG_ENTER_METHOD + "getAllDocumentTypes()" + LOG_CLOSE);
		List<DocumentType> docTypes = service.findAll();
		if (docTypes.size() == 0) {
			logger.info(LOG_ERROR + "docTypes were not found" + LOG_CLOSE);
			return new ResponseEntity<List<DocumentType>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of docTypes equals " + docTypes.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllDocumentTypes()" + LOG_CLOSE);
		return new ResponseEntity<List<DocumentType>>(docTypes, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<DocumentType> getDocumentTypeById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getDocumentTypeById()" + LOG_CLOSE);
		DocumentType docType = service.findById(id);
		if (docType == null) {
			logger.info(LOG_ERROR + "DocumentType with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<DocumentType>(docType, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "DocumentType with ID=" + id + " was found: " + docType + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getDocumentTypeById()" + LOG_CLOSE);
		return new ResponseEntity<DocumentType>(docType, HttpStatus.OK);
	}

}
