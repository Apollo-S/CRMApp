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
		logger.info(LOG_ENTER_METHOD + "getAllClientAddresses()" + LOG_CLOSE);
		List<ClientAddress> addresses = addressRepository.findAllClientAddressesByClientId(clientId);
		if (addresses.size() == 0) {
			logger.info(LOG_ERROR + "ClientAddresses were not found" + LOG_CLOSE);
			return new ResponseEntity<List<ClientAddress>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of ClientAddresses: " + addresses.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllClientAddresses()" + LOG_CLOSE);
		return new ResponseEntity<List<ClientAddress>>(addresses, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> getClientAddressById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getClientAddressById()" + LOG_CLOSE);
		ClientAddress address = addressRepository.findOne(id);
		if (address == null) {
			logger.info(LOG_ERROR + "ClientAccount with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<ClientAddress>(address, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_OUT_OF_METHOD + "getClientAddressById()" + LOG_CLOSE);
		return new ResponseEntity<ClientAddress>(address, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> addClientAddress(@PathVariable("clientId") int clientId,
			@RequestBody ClientAddress address) {
		logger.info(LOG_ENTER_METHOD + "addClientAddress()" + LOG_CLOSE);
		Client client = clientRepository.findOne(clientId);
		logger.info(LOG_TEXT + "Obtained Client with ID = " + client.getId() + LOG_CLOSE);
		address.setClient(client);
		address.setVersion(0);
		address = addressRepository.save(address);
		logger.info(LOG_TEXT + "ClientAddress added with ID=" + address.getId() + LOG_CLOSE);
		HttpHeaders header = new HttpHeaders();
		logger.info(LOG_OUT_OF_METHOD + "addClientAddress()" + LOG_CLOSE);
		return new ResponseEntity<ClientAddress>(address, header, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> updateClientAddress(@PathVariable("clientId") int clientId, 
			@RequestBody ClientAddress address) {
		logger.info(LOG_ENTER_METHOD + "updateClientAddress()" + LOG_CLOSE);
		Client client = clientRepository.findOne(clientId);
		address.setClient(client);
		logger.info(LOG_TEXT + "Client is setted to " + client + LOG_CLOSE);
		int actualVersionNumber = addressRepository.getOne(address.getId()).getVersion();
		address.setVersion(actualVersionNumber);
		address = addressRepository.save(address);
		logger.info(LOG_TEXT + "ClientAddress was updated: " + address + LOG_CLOSE);
		HttpHeaders header = new HttpHeaders();
		logger.info(LOG_OUT_OF_METHOD + "updateClientAddress()" + LOG_CLOSE);
		return new ResponseEntity<ClientAddress>(address, header, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClientAddress(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteClientAddress()" + LOG_CLOSE);
		addressRepository.delete(id);
		logger.info(LOG_TEXT + "ClientAccount with ID=" + id + " was deleted" + LOG_CLOSE);
		HttpHeaders header = new HttpHeaders();
		logger.info(LOG_OUT_OF_METHOD + "deleteClientAddress()" + LOG_CLOSE);
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
