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

import crmapp.app.entities.DocumentStatus;
import crmapp.app.services.DocumentStatusService;

@RestController
@RequestMapping(value = "/api/document-statuses")
public class DocumentStatusController extends BaseController<DocumentStatus, DocumentStatusService> {

	private static final Logger logger = LoggerFactory.getLogger(DocumentStatusController.class);

	@Autowired
	private DocumentStatusService service;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<DocumentStatus>> getAllDocumentStatuses() {
		logger.info(LOG_ENTER_METHOD + "getAllDocumentStatuses()" + LOG_CLOSE);
		List<DocumentStatus> docStatuses = service.findAll();
		if (docStatuses.size() == 0) {
			logger.info(LOG_ERROR + "DocumentStatuses were not found" + LOG_CLOSE);
			return new ResponseEntity<List<DocumentStatus>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of DocumentStatuses: " + docStatuses.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllDocumentStatuses()" + LOG_CLOSE);
		return new ResponseEntity<List<DocumentStatus>>(docStatuses, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<DocumentStatus> getDocumentStatusById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getDocumentStatusById()" + LOG_CLOSE);
		DocumentStatus docStatus = service.findById(id);
		if (docStatus == null) {
			logger.info(LOG_ERROR + "DocumentStatus with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<DocumentStatus>(docStatus, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "DocumentStatus with ID=" + id + " was found: " + docStatus + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getDocumentStatusById()" + LOG_CLOSE);
		return new ResponseEntity<DocumentStatus>(docStatus, HttpStatus.OK);
	}

}
