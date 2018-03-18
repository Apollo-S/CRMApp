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

import crmapp.app.entities.ClientAgreement;
import crmapp.app.repositories.ClientAgreementRepository;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class ClientAgreementController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ClientAgreementController.class);

	@Autowired
	private ClientAgreementRepository agreementRepository;

	@GetMapping(value = "/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientAgreement>> getAllAgreements() {
		logger.info("<==/////////// Entering to the getAllAgreements() method ... ///////////==>");
		List<ClientAgreement> agreements = agreementRepository.findAll();
		logger.info("<==/////////// Printing agreements: " + agreements + " ///////////==>");
		if (agreements.size() == 0) {
			return new ResponseEntity<List<ClientAgreement>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ClientAgreement>>(agreements, HttpStatus.OK);
	}

	@GetMapping(value = "/clients/{clientId}/agreements", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClientAgreement>> getAllAgreementsByClientId(@PathVariable("clientId") int clientId) {
		logger.info("<==/////////// Entering to the getAllAgreementsByClientId() method ... ///////////==>");
		List<ClientAgreement> agreements = agreementRepository.findAllAgreementsByClientId(clientId);
		logger.info("<==/////////// Printing agreements: " + agreements + " ///////////==>");
		if (agreements.size() == 0) {
			return new ResponseEntity<List<ClientAgreement>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ClientAgreement>>(agreements, HttpStatus.OK);
	}
	
	@GetMapping(value = { "/agreements/{id}", "/clients/{clientId}/agreements/{id}" }, headers = HEADER_JSON)
	public ResponseEntity<ClientAgreement> getAgreementById(@PathVariable(PARAM_ID) int id) {
		logger.info("<==/////////// Entering to the getAgreementById() method ... ///////////==>");
		ClientAgreement agreement = agreementRepository.findOne(id);
		if (agreement == null) {
			return new ResponseEntity<ClientAgreement>(agreement, HttpStatus.NOT_FOUND);
		}
		logger.info("<==/////////// Printing agreement: " + agreement + "///////////==>");
		return new ResponseEntity<ClientAgreement>(agreement, HttpStatus.OK);
	}
	
	@PostMapping(value = "/agreements", headers = HEADER_JSON)
	public ResponseEntity<Void> addAgreement(@RequestBody ClientAgreement agreement) {
		agreement = agreementRepository.save(agreement);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@PutMapping(value = "/agreements/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updateAgreement(@PathVariable(PARAM_ID) int id, @RequestBody ClientAgreement agreement) {
		agreement.setId(id);
		agreement.setVersion(agreementRepository.getOne(id).getVersion());
		agreement = agreementRepository.save(agreement);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}

	@DeleteMapping(value = { "/agreements/{id}", "/clients/{clientId}/agreements/{id}" }, headers = HEADER_JSON)
	public ResponseEntity<Void> deleteAgreement(@PathVariable(PARAM_ID) int id, @RequestBody ClientAgreement agreement) {
		agreement.setId(id);
		agreement.setVersion(agreementRepository.getOne(id).getVersion());
		agreementRepository.delete(agreement);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
