package crmapp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.MailOutput;
import crmapp.app.repositories.MailOutputRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/mail-outputs")
public class MailOutputController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(MailOutputController.class);

	@Autowired
	private MailOutputRepository mailRepository;
	
	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<MailOutput>> getAllMailOutputs() {
		logger.info(LOG_ENTER_METHOD + "getAllMailOutputs()" + LOG_CLOSE);
		List<MailOutput> outputs = mailRepository.findAll();
		if (outputs.size() == 0) {
			logger.info(LOG_ERROR + "MailOutputs were not found" + LOG_CLOSE);
			return new ResponseEntity<List<MailOutput>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of MailOutputs: " + outputs.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllMailOutputs()" + LOG_CLOSE);
		return new ResponseEntity<List<MailOutput>>(outputs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<MailOutput> getMailOutputById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getMailOutputById()" + LOG_CLOSE);
		MailOutput output = mailRepository.findOne(id);
		if (output == null) {
			logger.info(LOG_ERROR + "MailOutput with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<MailOutput>(output, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "MailOutput with ID=" + id + " was found: " + output + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getMailOutputById()" + LOG_CLOSE);
		return new ResponseEntity<MailOutput>(output, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteMailOutput(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteMailOutput()" + LOG_CLOSE);
		mailRepository.delete(id);
		logger.info(LOG_TEXT + "MailOutput with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteMailOutput()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
