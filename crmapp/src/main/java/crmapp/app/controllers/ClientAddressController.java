package crmapp.app.controllers;

import java.util.List;

import crmapp.app.services.ClientAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.ClientAddress;

@RestController
@RequestMapping(value = "/api/clients/{clientId}/addresses")
public class ClientAddressController extends BaseController<ClientAddress, ClientAddressService> {

	private static final Logger logger = LoggerFactory.getLogger(ClientAddressController.class);
	private final ClientAddressService addressService;

	@Autowired
	public ClientAddressController(ClientAddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<ClientAddress>> getAllClientAddresses(@PathVariable("clientId") Integer clientId) {
		logger.info(LOG_ENTER_METHOD + "getAllClientAddresses()" + LOG_CLOSE);
		ResponseEntity<List<ClientAddress>> responseEntity = super.getAllEntities();
		logger.info(LOG_OUT_OF_METHOD + "getAllClientAddresses()" + LOG_CLOSE);
		return responseEntity;
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> getClientAddressById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getClientAddressById()" + LOG_CLOSE);
		ClientAddress address = super.service.findById(id);
		if (address == null) {
			logger.info(LOG_ERROR + "ClientAccount with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_OUT_OF_METHOD + "getClientAddressById()" + LOG_CLOSE);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> addClientAddress(@PathVariable("clientId") int clientId,
														  @RequestBody ClientAddress address) {
		logger.info(LOG_ENTER_METHOD + "addClientAddress()" + LOG_CLOSE);
		ClientAddress savedAddress = addressService.save(clientId, address);
		logger.info(LOG_TEXT + "ClientAddress added with ID=" + savedAddress.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addClientAddress()" + LOG_CLOSE);
		return new ResponseEntity<>(savedAddress, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAddress> updateClientAddress(@PathVariable("clientId") int clientId,
															 @RequestBody ClientAddress address) {
		logger.info(LOG_ENTER_METHOD + "updateClientAddress()" + LOG_CLOSE);
		ClientAddress updatedAddress = addressService.updateWithClientId(clientId, address);
		logger.info(LOG_TEXT + "ClientAddress was updated: " + updatedAddress + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateClientAddress()" + LOG_CLOSE);
		return new ResponseEntity<>(updatedAddress, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClientAddress(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteClientAddress()" + LOG_CLOSE);
		addressService.delete(id);
		logger.info(LOG_TEXT + "ClientAccount with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteClientAddress()" + LOG_CLOSE);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
