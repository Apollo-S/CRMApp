package crmapp.app.controllers;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crmapp.app.entities.ClientAgreement;
import crmapp.app.services.ClientAgreementService;

@RestController
@RequestMapping(value = "/api")
public class ClientAgreementController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ClientAgreementController.class);

	@Autowired
	private ClientAgreementService service;

	@GetMapping(value = "/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientAgreement>> getAllAgreements() {
		logger.info(LOG_ENTER_METHOD + "getAllAgreements()" + LOG_CLOSE);
		List<ClientAgreement> agreements = service.findAll();
		if (agreements.size() == 0) {
			logger.info(LOG_ERROR + "ClientAgreements were not found" + LOG_CLOSE);
			return new ResponseEntity<List<ClientAgreement>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of ClientAgreements: " + agreements.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllAgreements()" + LOG_CLOSE);
		return new ResponseEntity<List<ClientAgreement>>(agreements, HttpStatus.OK);
	}

	@GetMapping(value = "/clients/{clientId}/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientAgreement>> getAllAgreementsByClientId(@PathVariable("clientId") int clientId) {
		logger.info(LOG_ENTER_METHOD + "getAllAgreementsByClientId()" + LOG_CLOSE);
		List<ClientAgreement> agreements = service.findAllByClientId(clientId);
		if (agreements.size() == 0) {
			logger.info(LOG_ERROR + "ClientAgreements by clientIdwere not found" + LOG_CLOSE);
			return new ResponseEntity<List<ClientAgreement>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of ClientAgreements by clientId: " + agreements.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllAgreementsByClientId()" + LOG_CLOSE);
		return new ResponseEntity<List<ClientAgreement>>(agreements, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/agreements/{id}", "/clients/{clientId}/agreements/{id}" }, headers = HEADER_JSON)
	public ResponseEntity<ClientAgreement> getAgreementById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getAgreementById()" + LOG_CLOSE);
		ClientAgreement agreement = service.findById(id);
		if (agreement == null) {
			logger.info(LOG_ERROR + "ClientAgreement with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<ClientAgreement>(agreement, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "ClientAgreement with ID=" + id + " was found: " + agreement + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAgreementById()" + LOG_CLOSE);
		return new ResponseEntity<ClientAgreement>(agreement, HttpStatus.OK);
	}

	@PostMapping(value = "/agreements", headers = HEADER_JSON)
	public ResponseEntity<ClientAgreement> addAgreement(@RequestBody ClientAgreement agreement) {
		logger.info(LOG_ENTER_METHOD + "addAgreement()" + LOG_CLOSE);
		agreement = service.save(agreement);
		logger.info(LOG_TEXT + "ClientAgreement added with ID=" + agreement.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addAgreement()" + LOG_CLOSE);
		return new ResponseEntity<ClientAgreement>(agreement, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/agreements/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAgreement> updateAgreement(@PathVariable(PARAM_ID) int id,
			@RequestBody ClientAgreement agreement) {
		logger.info(LOG_ENTER_METHOD + "updateAgreement()" + LOG_CLOSE);
		agreement = service.update(id, agreement);
		logger.info(LOG_TEXT + "ClientAgreement with ID=" + id + " was updated: " + agreement + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateAgreement()" + LOG_CLOSE);
		return new ResponseEntity<ClientAgreement>(agreement, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = { "/agreements/{id}", "/clients/{clientId}/agreements/{id}" }, headers = HEADER_JSON)
	public ResponseEntity<Void> deleteAgreement(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteAgreement()" + LOG_CLOSE);
		service.delete(id);
		logger.info(LOG_TEXT + "ClientAgreement with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteAgreement()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}