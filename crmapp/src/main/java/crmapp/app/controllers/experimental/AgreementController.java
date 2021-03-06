package crmapp.app.controllers.experimental;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.Agreement;
import crmapp.app.entities.AgreementType;
import crmapp.app.services.AgreementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AgreementController extends BaseController<Agreement, AgreementService> {

	private static final Logger logger = LoggerFactory.getLogger(AgreementController.class);

	@Autowired
	private AgreementService service;

//	@GetMapping(value = "/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Agreement>> getAllAgreements() {
		logger.info(LOG_ENTER_METHOD + "getAllAgreements()" + LOG_CLOSE);
		List<Agreement> agreements = service.findAll();
		if (agreements.size() == 0) {
			logger.info(LOG_ERROR + "Agreements were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of Agreements: " + agreements.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllAgreements()" + LOG_CLOSE);
		return new ResponseEntity<>(agreements, HttpStatus.OK);
	}

//	@PostMapping(value = "/agreements/type", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Agreement>> getAllAgreementsByType(@RequestBody AgreementType agrType) {
		logger.info(LOG_ENTER_METHOD + "getAllAgreementsByType()" + LOG_CLOSE);
		List<Agreement> agreements = service.findAllAgreementsByType(agrType);
		if (agreements.size() == 0) {
			logger.info(LOG_ERROR + "Agreements by type {" + agrType.getCode() + "} were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of Agreements by type: " + agreements.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllAgreementsByType()" + LOG_CLOSE);
		return new ResponseEntity<>(agreements, HttpStatus.OK);
	}

//	@GetMapping(value = "/clients/{clientId}/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Agreement>> getAllAgreementsByClientId(@PathVariable("clientId") int clientId) {
		logger.info(LOG_ENTER_METHOD + "getAllAgreementsByClientId()" + LOG_CLOSE);
		List<Agreement> agreements = service.findAllByClientId(clientId);
		if (agreements.size() == 0) {
			logger.info(LOG_ERROR + "Agreements by clientId were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of Agreements by clientId: " + agreements.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllAgreementsByClientId()" + LOG_CLOSE);
		return new ResponseEntity<>(agreements, HttpStatus.OK);
	}

//	@GetMapping(value = { "/agreements/{id}", "/clients/{clientId}/agreements/{id}" }, headers = HEADER_JSON)
	public ResponseEntity<Agreement> getAgreementById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getAgreementById()" + LOG_CLOSE);
		Agreement agreement = service.findById(id);
		if (agreement == null) {
			logger.info(LOG_ERROR + "Agreement with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "Agreement with ID=" + id + " was found: " + agreement + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAgreementById()" + LOG_CLOSE);
		return new ResponseEntity<>(agreement, HttpStatus.OK);
	}

//	@PostMapping(value = "/agreements", headers = HEADER_JSON)
	public ResponseEntity<Agreement> addAgreement(@RequestBody Agreement agreement) {
		logger.info(LOG_ENTER_METHOD + "addAgreement()" + LOG_CLOSE);
		agreement = service.save(agreement);
		logger.info(LOG_TEXT + "Agreement added with ID=" + agreement.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addAgreement()" + LOG_CLOSE);
		return new ResponseEntity<>(agreement, new HttpHeaders(), HttpStatus.CREATED);
	}

//	@PutMapping(value = "/agreements/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updateAgreement(@PathVariable(PARAM_ID) int id,
			@RequestBody Agreement agreement) {
		logger.info(LOG_ENTER_METHOD + "updateAgreement()" + LOG_CLOSE);
		service.update(id, agreement);
		logger.info(LOG_TEXT + "Agreement with ID=" + id + " was updated: " + agreement + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateAgreement()" + LOG_CLOSE);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
	}

//	@DeleteMapping(value = { "/agreements/{id}", "/clients/{clientId}/agreements/{id}" }, headers = HEADER_JSON)
	public ResponseEntity<Void> deleteAgreement(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteAgreement()" + LOG_CLOSE);
		service.delete(id);
		logger.info(LOG_TEXT + "Agreement with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteAgreement()" + LOG_CLOSE);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}