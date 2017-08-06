package crmapp.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.Agreement;
import crmapp.app.repositories.AgreementRepository;

@RestController
@Transactional
@RequestMapping(value = "/agreement")
public class AgreementController extends BaseController {

	
	@Autowired
	private AgreementRepository agreementRepository;
	
	@RequestMapping(value = VALUE_GET_ALL, method = RequestMethod.GET, headers = HEADER_JSON) 
	public ResponseEntity<List<Agreement>> getAllAgreements() {
		List<Agreement> agreements = agreementRepository.findAll();
		if(agreements.size() == 0) {
			return new ResponseEntity<List<Agreement>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Agreement>>(agreements, HttpStatus.OK);
	}
	
	@RequestMapping(value = VALUE_ADD, method = RequestMethod.POST, headers = HEADER_JSON)
	public ResponseEntity<Void> addAgreement(@RequestBody Agreement agreement) {
		agreement = agreementRepository.save(agreement);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
	
	@Transactional
	@RequestMapping(value = VALUE_UPDATE, method = RequestMethod.PUT, headers = HEADER_JSON)
	public ResponseEntity<Void> updateAgreement(@PathVariable(PARAM_ID) int id, @RequestBody Agreement agreement) {
		agreement.setId(id);
		agreement.setVersion(agreementRepository.getOne(id).getVersion());
		agreement = agreementRepository.save(agreement);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	@RequestMapping(value = VALUE_DELETE, method = RequestMethod.DELETE, headers = HEADER_JSON)
	public ResponseEntity<Void> deleteAgreement(@PathVariable(PARAM_ID) int id, @RequestBody Agreement agreement) {
		agreement.setId(id);
		agreement.setVersion(agreementRepository.getOne(id).getVersion());
		agreementRepository.delete(agreement);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
