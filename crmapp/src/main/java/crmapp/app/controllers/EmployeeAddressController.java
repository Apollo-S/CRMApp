package crmapp.app.controllers;

import java.util.List;

import crmapp.app.entities.Address;
import crmapp.app.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.Address;
import crmapp.app.repositories.AddressRepository;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class EmployeeAddressController extends BaseController {

	@Autowired
	private AddressRepository addressRepository;

	@GetMapping(value = "/employees/{employeeId}/addresses", headers = HEADER_JSON)
	public ResponseEntity<List<Address>> getAllAddressesByEmployeeId(
			@PathVariable("employeeId") Integer employeeId) {
		List<Address> addresses = addressRepository.findAllAddressesByEmployeeId(employeeId);
		if (addresses.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}
	
	@GetMapping(value = "/employees/addresses", headers = HEADER_JSON)
	public ResponseEntity<List<Address>> getAllAddresses() {
		List<Address> addresses = addressRepository.findAll();
		if (addresses.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@GetMapping(value = "/employees/addresses/{id}", headers = HEADER_JSON)
	public ResponseEntity<Address> getAddressById(@PathVariable(PARAM_ID) int id) {
		Address address = addressRepository.findOne(id);
		if (address == null) {
			return new ResponseEntity<>(address, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

}
