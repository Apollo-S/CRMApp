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

import crmapp.app.entities.ClientAddress;
import crmapp.app.repositories.ClientAddressRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/clients")
public class ClientAddressController extends BaseController {

	@Autowired
	private ClientAddressRepository addressRepository;

	@GetMapping(value = "/{clientId}/addresses", headers = HEADER_JSON)
	public ResponseEntity<List<ClientAddress>> getAllClientAddresses(@PathVariable("clientId") Integer clientId) {
		List<ClientAddress> addresses = addressRepository.findAllClientAddressesByClientId(clientId);
		if (addresses.size() == 0) {
			return new ResponseEntity<List<ClientAddress>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ClientAddress>>(addresses, HttpStatus.OK);
	}

	@GetMapping(value = "/addresses/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> getClientAddressById(@PathVariable(PARAM_ID) int id) {
		ClientAddress address = addressRepository.findOne(id);
		if (address == null) {
			return new ResponseEntity<ClientAddress>(address, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ClientAddress>(address, HttpStatus.OK);
	}

}
