package crmapp.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.EmployeeAddress;
import crmapp.app.repositories.EmployeeAddressRepository;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class EmployeeAddressController extends BaseController {

	@Autowired
	private EmployeeAddressRepository addressRepository;

	@GetMapping(value = "/employees/{employeeId}/addresses", headers = HEADER_JSON)
	public ResponseEntity<List<EmployeeAddress>> getAllEmployeeAddressesByEmployeeId(
			@PathVariable("employeeId") Integer employeeId) {
		List<EmployeeAddress> addresses = addressRepository.findAllEmployeeAddressesByEmployeeId(employeeId);
		if (addresses.size() == 0) {
			return new ResponseEntity<List<EmployeeAddress>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EmployeeAddress>>(addresses, HttpStatus.OK);
	}
	
	@GetMapping(value = "/employees/addresses", headers = HEADER_JSON)
	public ResponseEntity<List<EmployeeAddress>> getAllEmployeeAddresses() {
		List<EmployeeAddress> addresses = addressRepository.findAll();
		if (addresses.size() == 0) {
			return new ResponseEntity<List<EmployeeAddress>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EmployeeAddress>>(addresses, HttpStatus.OK);
	}

	@GetMapping(value = "/employees/addresses/{id}", headers = HEADER_JSON)
	public ResponseEntity<EmployeeAddress> getEmployeeAddressById(@PathVariable(PARAM_ID) int id) {
		EmployeeAddress address = addressRepository.findOne(id);
		if (address == null) {
			return new ResponseEntity<EmployeeAddress>(address, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EmployeeAddress>(address, HttpStatus.OK);
	}

}
