package crmapp.app.controllers;

import java.util.List;

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

import crmapp.app.entities.ClientAccount;
import crmapp.app.repositories.ClientAccountRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/clients")
public class ClientAccountController extends BaseController {

	@Autowired
	private ClientAccountRepository accountRepository;

	@GetMapping(value = "/{clientId}/accounts", headers = HEADER_JSON)
	public ResponseEntity<List<ClientAccount>> getAllClientAccountsByClientId(
			@PathVariable("clientId") Integer clientId) {
		List<ClientAccount> accounts = accountRepository.findAllByClientId(clientId);
		if (accounts.size() == 0) {
			return new ResponseEntity<List<ClientAccount>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ClientAccount>>(accounts, HttpStatus.OK);
	}

	@GetMapping(value = "/accounts/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> getClientAccountById(@PathVariable(PARAM_ID) int id) {
		ClientAccount account = accountRepository.findOne(id);
		if (account == null) {
			return new ResponseEntity<ClientAccount>(account, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ClientAccount>(account, HttpStatus.OK);
	}

	@PostMapping(value = "/accounts/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> addClientAccount(@RequestBody ClientAccount account) {
		account.setVersion(0);
		account = accountRepository.save(account);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/accounts/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updateClientAccount(@PathVariable(PARAM_ID) int id,
			@RequestBody ClientAccount account) {
		account.setId(id);
		account.setVersion(accountRepository.getOne(id).getVersion());
		account = accountRepository.save(account);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/accounts/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClient(@PathVariable(PARAM_ID) int id) {
		accountRepository.delete(id);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
