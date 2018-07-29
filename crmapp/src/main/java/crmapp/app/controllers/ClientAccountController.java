package crmapp.app.controllers;

import java.util.List;

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

import crmapp.app.entities.ClientAccount;
import crmapp.app.services.ClientAccountService;

@RestController
@RequestMapping(value = "/api/clients/{clientId}/accounts")
public class ClientAccountController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ClientAccountController.class);

	@Autowired
	private ClientAccountService service;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<ClientAccount>> getAllClientAccountsByClientId(
			@PathVariable("clientId") Integer clientId) {
		logger.info(LOG_ENTER_METHOD + "getAllClientAccountsByClientId()" + LOG_CLOSE);
		List<ClientAccount> accounts = service.findAllByClientId(clientId);
		if (accounts.size() == 0) {
			logger.info(LOG_ERROR + "ClientAccounts were not found" + LOG_CLOSE);
			return new ResponseEntity<List<ClientAccount>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of ClientAccounts: " + accounts.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllClientAccountsByClientId()" + LOG_CLOSE);
		return new ResponseEntity<List<ClientAccount>>(accounts, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> getClientAccountById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getClientAccountById()" + LOG_CLOSE);
		ClientAccount account = service.getById(id);
		if (account == null) {
			logger.info(LOG_ERROR + "ClientAccount with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<ClientAccount>(account, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "ClientAccount with ID=" + id + " was found: " + account + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getClientAccountById()" + LOG_CLOSE);
		return new ResponseEntity<ClientAccount>(account, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> addClientAccount(@PathVariable("clientId") int clientId,
			@RequestBody ClientAccount account) {
		logger.info(LOG_ENTER_METHOD + "addClientAccount()" + LOG_CLOSE);
		account = service.save(clientId, account);
		logger.info(LOG_TEXT + "ClientAccount added with ID=" + account.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addClientAccount()" + LOG_CLOSE);
		return new ResponseEntity<ClientAccount>(account, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> updateClientAccount(@PathVariable("clientId") int clientId,
			@RequestBody ClientAccount account) {
		logger.info(LOG_ENTER_METHOD + "updateClientAccount()" + LOG_CLOSE);
		account = service.updateWithClientId(clientId, account);
		logger.info(LOG_TEXT + "ClientAccount was updated: " + account + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateClientAccount()" + LOG_CLOSE);
		return new ResponseEntity<ClientAccount>(account, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClientAccount(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteClientAccount()" + LOG_CLOSE);
		service.delete(id);
		logger.info(LOG_TEXT + "ClientAccount with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteClientAccount()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
