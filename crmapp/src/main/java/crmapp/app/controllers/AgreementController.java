package crmapp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import crmapp.app.entities.Agreement;
import crmapp.app.repositories.AgreementRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/agreements")
public class AgreementController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AgreementController.class);

	@Autowired
	private AgreementRepository agreementRepository;

	@GetMapping(value = REQUEST_MAPPING_EMPTY, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Agreement>> getAllAgreements() {
		logger.info("<==/////////// Entering to the getAllAgreements() method ... ///////////==>");
		List<Agreement> agreements = agreementRepository.findAll();
		logger.info("<==/////////// Printing agreements: " + agreements + " ///////////==>");
		if (agreements.size() == 0) {
			return new ResponseEntity<List<Agreement>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Agreement>>(agreements, HttpStatus.OK);
	}

	@GetMapping(value = REQUEST_MAPPING_BY_ID, headers = HEADER_JSON)
	public ResponseEntity<Agreement> getContractorById(@PathVariable(PARAM_ID) int id) {
		Agreement agreement = agreementRepository.findOne(id);
		if (agreement == null) {
			return new ResponseEntity<Agreement>(agreement, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Agreement>(agreement, HttpStatus.OK);
	}

	@PostMapping(value = REQUEST_MAPPING_EMPTY, headers = HEADER_JSON)
	public ResponseEntity<Void> addAgreement(@RequestBody Agreement agreement) {
		agreement = agreementRepository.save(agreement);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@PutMapping(value = REQUEST_MAPPING_BY_ID, headers = HEADER_JSON)
	public ResponseEntity<Void> updateAgreement(@PathVariable(PARAM_ID) int id, @RequestBody Agreement agreement) {
		agreement.setId(id);
		agreement.setVersion(agreementRepository.getOne(id).getVersion());
		agreement = agreementRepository.save(agreement);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}

	@DeleteMapping(value = REQUEST_MAPPING_BY_ID, headers = HEADER_JSON)
	public ResponseEntity<Void> deleteAgreement(@PathVariable(PARAM_ID) int id, @RequestBody Agreement agreement) {
		agreement.setId(id);
		agreement.setVersion(agreementRepository.getOne(id).getVersion());
		agreementRepository.delete(agreement);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
