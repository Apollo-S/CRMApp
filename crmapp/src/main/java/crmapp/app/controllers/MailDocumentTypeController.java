package crmapp.app.controllers;

import java.util.List;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.services.MailDocumentTypeService;
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

import crmapp.app.entities.MailDocumentType;
import crmapp.app.repositories.MailDocumentTypeRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/mail-document-types")
public class MailDocumentTypeController extends BaseController<MailDocumentType, MailDocumentTypeService> {

	private static final Logger logger = LoggerFactory.getLogger(MailDocumentTypeController.class);

	@Autowired
	private MailDocumentTypeRepository docTypeRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<MailDocumentType>> getAllMailDocumentTypes() {
		logger.info(LOG_ENTER_METHOD + "getAllMailDocumentTypes()" + LOG_CLOSE);
		List<MailDocumentType> docTypes = docTypeRepository.findAll();
		if (docTypes.size() == 0) {
			logger.info(LOG_ERROR + "MailDocumentTypes were not found" + LOG_CLOSE);
			return new ResponseEntity<List<MailDocumentType>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of MailDocumentTypes equals " + docTypes.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllMailDocumentTypes()" + LOG_CLOSE);
		return new ResponseEntity<List<MailDocumentType>>(docTypes, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<MailDocumentType> getMailDocumentTypeById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getMailDocumentTypeById()" + LOG_CLOSE);
		MailDocumentType docType = docTypeRepository.findOne(id);
		if (docType == null) {
			logger.info(LOG_ERROR + "MailDocumentType with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<MailDocumentType>(docType, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "MailDocumentType with ID=" + id + " was found: " + docType + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getMailDocumentTypeById()" + LOG_CLOSE);
		return new ResponseEntity<MailDocumentType>(docType, HttpStatus.OK);
	}

}
