package crmapp.app.controllers;

import java.util.List;

import crmapp.app.entities.Address;
import crmapp.app.services.AddressService;
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

@RestController
@RequestMapping(value = "/api/clients/{clientId}/addresses")
public class AddressController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Address>> getAllAddressesByClientId(@PathVariable("clientId") Integer clientId) {
		logger.info(LOG_ENTER_METHOD + "getAllAddressesByClientId()" + LOG_CLOSE);
		List<Address> addresses = addressService.findAllByClientId(clientId);
		if (addresses.size() == 0) {
			logger.info(LOG_ERROR + "Addresses were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of Addresses: " + addresses.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllAddressesByClientId()" + LOG_CLOSE);
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Address> getAddressById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getAddressById()" + LOG_CLOSE);
		Address address = addressService.findById(id);
		if (address == null) {
			logger.info(LOG_ERROR + "Address with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_OUT_OF_METHOD + "getAddressById()" + LOG_CLOSE);
		return new ResponseEntity<>(address, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Address> addAddress(@PathVariable("clientId") int clientId,
														  @RequestBody Address address) {
		logger.info(LOG_ENTER_METHOD + "addAddress()" + LOG_CLOSE);
		Address savedAddress = addressService.save(clientId, address);
		logger.info(LOG_TEXT + "Address added with ID=" + savedAddress.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addAddress()" + LOG_CLOSE);
		return new ResponseEntity<>(savedAddress, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Address> updateAddress(@PathVariable("clientId") int clientId,
															 @RequestBody Address address) {
		logger.info(LOG_ENTER_METHOD + "updateAddress()" + LOG_CLOSE);
		Address updatedAddress = addressService.updateWithClientId(clientId, address);
		logger.info(LOG_TEXT + "Address was updated: " + updatedAddress + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateAddress()" + LOG_CLOSE);
		return new ResponseEntity<>(updatedAddress, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteAddress(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteAddress()" + LOG_CLOSE);
		addressService.delete(id);
		logger.info(LOG_TEXT + "ClientAccount with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteAddress()" + LOG_CLOSE);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
