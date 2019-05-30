package crmapp.app.controllers;

import java.util.List;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.services.MailInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crmapp.app.entities.MailInput;
import crmapp.app.repositories.MailInputRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/mail-inputs")
public class MailInputController extends BaseController<MailInput, MailInputService> {

	private static final Logger logger = LoggerFactory.getLogger(MailInputController.class);

	@Autowired
	private MailInputRepository mailRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<MailInput>> getAllMailInputs() {
		logger.info(LOG_ENTER_METHOD + "getAllMailInputs()" + LOG_CLOSE);
		List<MailInput> inputs = mailRepository.findAll(new Sort(Direction.DESC, "number"));
		if (inputs.size() == 0) {
			logger.info(LOG_ERROR + "MailInputs were not found" + LOG_CLOSE);
			return new ResponseEntity<List<MailInput>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of MailInputs: " + inputs.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllMailInputs()" + LOG_CLOSE);
		return new ResponseEntity<List<MailInput>>(inputs, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<MailInput> getMailInputById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getMailInputById()" + LOG_CLOSE);
		MailInput input = mailRepository.findOne(id);
		if (input == null) {
			logger.info(LOG_ERROR + "MailInput with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<MailInput>(input, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "MailInput with ID=" + id + " was found: " + input + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getMailInputById()" + LOG_CLOSE);
		return new ResponseEntity<MailInput>(input, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<MailInput> addMailInput(@RequestBody MailInput input) {
		logger.info(LOG_ENTER_METHOD + "addMailInput()" + LOG_CLOSE);
		int maxMailInputNumber = mailRepository.getMaxMailInputNumber();
		input.setNumber(Integer.toString(++maxMailInputNumber));
		input.setVersion(0);
		input = mailRepository.save(input);
		logger.info(LOG_TEXT + "MailInput added with ID=" + input.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addMailInput()" + LOG_CLOSE);
		return new ResponseEntity<MailInput>(input, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<MailInput> updateMailInput(@PathVariable(PARAM_ID) int id,
			@RequestBody MailInput input) {
		logger.info(LOG_ENTER_METHOD + "updateMailInput()" + LOG_CLOSE);
		input.setId(id);
		int actualVersionNumber = mailRepository.getOne(id).getVersion();
		input.setVersion(actualVersionNumber);
		input = mailRepository.save(input);
		logger.info(LOG_TEXT + "MailInput with ID=" + id + " was updated: " + input + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateMailInput()" + LOG_CLOSE);
		return new ResponseEntity<MailInput>(input, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteMailInput(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteMailInput()" + LOG_CLOSE);
		mailRepository.delete(id);
		logger.info(LOG_TEXT + "MailInput with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteMailInput()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
