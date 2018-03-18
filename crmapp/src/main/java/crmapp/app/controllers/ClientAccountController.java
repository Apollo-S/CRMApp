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
import crmapp.app.entities.ClientAccount;
import crmapp.app.repositories.ClientAccountRepository;
import crmapp.app.repositories.ClientRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/clients/{clientId}/accounts")
public class ClientAccountController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientAccountController.class);

	@Autowired
	private ClientAccountRepository accountRepository;
	
	@Autowired
	private ClientRepository clientRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<ClientAccount>> getAllClientAccountsByClientId(
			@PathVariable("clientId") Integer clientId) {
		List<ClientAccount> accounts = accountRepository.findAllByClientId(clientId);
		if (accounts.size() == 0) {
			return new ResponseEntity<List<ClientAccount>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ClientAccount>>(accounts, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> getClientAccountById(@PathVariable(PARAM_ID) int id) {
		logger.info("<==/////////// Entering to the getClientAccountById() method ... ///////////==>");
		ClientAccount account = accountRepository.findOne(id);
		if (account == null) {
			return new ResponseEntity<ClientAccount>(account, HttpStatus.NOT_FOUND);
		}
		logger.info("<==/////////// Printing ClientAccount: " + account + "///////////==>");
		return new ResponseEntity<ClientAccount>(account, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> addClientAccount(@PathVariable("clientId") int clientId,
			@RequestBody ClientAccount account) {
		logger.info("<==/////////// Entering to the addClientAccount() method ... ///////////==>");
		Client client = clientRepository.findOne(clientId);
		logger.info("<==/////////// Getting the client with ID = " + client.getId() + " ///////////==>");
		account.setClient(client);
		account.setVersion(0);
		account = accountRepository.save(account);
		logger.info("<==/////////// Saving of ClientAccount with ID = " + account.getId()
				+ " was successfull ... ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<ClientAccount>(account, header, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> updateClientAccount(@PathVariable("clientId") int clientId,
			@RequestBody ClientAccount account) {
		logger.info("<==/////////// Entering to the updateClientAccount() method ... ///////////==>");
		Client client = clientRepository.findOne(clientId);
		account.setClient(client);
		logger.info("<==/////////// Client is setted to " + client + "///////////==>");
		int actualVersionNumber = accountRepository.getOne(account.getId()).getVersion();
		account.setVersion(actualVersionNumber);
		logger.info("<==/////////// Printing account: " + account + "///////////==>");
		account = accountRepository.save(account);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<ClientAccount>(account, header, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClient(@PathVariable(PARAM_ID) int id) {
		accountRepository.delete(id);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
