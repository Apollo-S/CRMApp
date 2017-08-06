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

import crmapp.app.entities.Contractor;
import crmapp.app.repositories.ContractorRepository;

@RestController
@Transactional
@RequestMapping(value = "/contractor")
public class ContractorController extends BaseController {
	
	@Autowired
	private ContractorRepository contractorRepository;
	
	@RequestMapping(value = VALUE_GET_ALL, method = RequestMethod.GET, headers = HEADER_JSON) 
	public ResponseEntity<List<Contractor>> getAllContractors() {
		List<Contractor> contractors = contractorRepository.findAll();
		if(contractors.size() == 0) {
			return new ResponseEntity<List<Contractor>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Contractor>>(contractors, HttpStatus.OK);
	}
	
	@RequestMapping(value = VALUE_GET_BY_ID, method = RequestMethod.GET, headers = HEADER_JSON) 
	public ResponseEntity<Contractor> getContractorById(@PathVariable(PARAM_ID) int id) {
		Contractor contractor = contractorRepository.findOne(id);
		if(contractor == null) {
			return new ResponseEntity<Contractor>(contractor, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Contractor>(contractor, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = VALUE_ADD, method = RequestMethod.POST, headers = HEADER_JSON)
	public ResponseEntity<Void> addContractor(@RequestBody Contractor contractor) {
		contractor = contractorRepository.save(contractor);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
	
	@Transactional
	@RequestMapping(value = VALUE_UPDATE, method = RequestMethod.PUT, headers = HEADER_JSON)
	public ResponseEntity<Void> updateContractor(@PathVariable(PARAM_ID) int id, @RequestBody Contractor contractor) {
		contractor.setId(id);
		contractor.setVersion(contractorRepository.getOne(id).getVersion());
		contractor = contractorRepository.save(contractor);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	@RequestMapping(value = VALUE_DELETE, method = RequestMethod.DELETE, headers = HEADER_JSON)
	public ResponseEntity<Void> deleteContractor(@PathVariable(PARAM_ID) int id, @RequestBody Contractor contractor) {
		contractor.setId(id);
		contractor.setVersion(contractorRepository.getOne(id).getVersion());
		contractorRepository.delete(contractor);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
