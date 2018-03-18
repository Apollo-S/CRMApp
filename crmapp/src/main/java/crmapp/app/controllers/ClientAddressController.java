package crmapp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import crmapp.app.entities.Client;
import crmapp.app.entities.ClientAddress;
import crmapp.app.repositories.ClientAddressRepository;
import crmapp.app.repositories.ClientRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/clients/{clientId}/addresses")
public class ClientAddressController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ClientAddressController.class);

	@Autowired
	private ClientAddressRepository addressRepository;

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<ClientAddress>> getAllClientAddresses(@PathVariable("clientId") Integer clientId) {
		List<ClientAddress> addresses = addressRepository.findAllClientAddressesByClientId(clientId);
		if (addresses.size() == 0) {
			return new ResponseEntity<List<ClientAddress>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ClientAddress>>(addresses, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> getClientAddressById(@PathVariable(PARAM_ID) int id) {
		logger.info("<==/////////// Entering to the getClientAddressById() method ... ///////////==>");
		ClientAddress address = addressRepository.findOne(id);
		if (address == null) {
			return new ResponseEntity<ClientAddress>(address, HttpStatus.NOT_FOUND);
		}
		logger.info("<==/////////// Printing ClientAddress: " + address + "///////////==>");
		return new ResponseEntity<ClientAddress>(address, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> addClientAddress(@PathVariable("clientId") int clientId,
			@RequestBody ClientAddress address) {
		logger.info("<==/////////// Entering to the addClientAddress() method ... ///////////==>");
		Client client = clientRepository.findOne(clientId);
		logger.info("<==/////////// Getting the client with ID = " + client.getId() + " ///////////==>");
		address.setClient(client);
		address.setVersion(0);
		address = addressRepository.save(address);
		logger.info("<==/////////// Saving of ClientAddress with ID = " + address.getId()
				+ " was successfull ... ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<ClientAddress>(address, header, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> updateClientAddress(@PathVariable("clientId") int clientId, 
			@RequestBody ClientAddress address) {
		logger.info("<==/////////// Entering to the updateClientAddress() method ... ///////////==>");
		Client client = clientRepository.findOne(clientId);
		address.setClient(client);
		logger.info("<==/////////// Client is setted to " + client + "///////////==>");
		int actualVersionNumber = addressRepository.getOne(address.getId()).getVersion();
		address.setVersion(actualVersionNumber);
		logger.info("<==/////////// Printing address: " + address + "///////////==>");
		address = addressRepository.save(address);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<ClientAddress>(address, header, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClientAddress(@PathVariable(PARAM_ID) int id) {
		addressRepository.delete(id);
		logger.info("<==/////////// ClientAddress  with ID = " + id + " was deleted successfully!!! ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
