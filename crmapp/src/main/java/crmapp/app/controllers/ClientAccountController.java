package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.ClientAccount;
import crmapp.app.services.ClientAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clients/{clientId}/accounts")
public class ClientAccountController extends ExtendedBaseController<ClientAccount, ClientAccountService> {

	private static final Logger logger = LoggerFactory.getLogger(ClientAccountController.class);
	private final ClientAccountService accountService;

	@Autowired
	public ClientAccountController(ClientAccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<ClientAccount>> getAllClientAccountsByClientId(
			@PathVariable("clientId") Integer clientId) {
		return super.getAllFilterBy("client", clientId);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> getClientAccountById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityById(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> addClientAccount(@PathVariable("clientId") int clientId,
			@RequestBody ClientAccount account) {
		logger.info(LOG_ENTER_METHOD + "addClientAccount()" + LOG_CLOSE);
		account = accountService.save(clientId, account);
		logger.info(LOG_TEXT + "ClientAccount added with ID=" + account.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addClientAccount()" + LOG_CLOSE);
		return new ResponseEntity<>(account, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientAccount> updateClientAccount(@PathVariable int clientId,
			@RequestBody ClientAccount account, @PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "updateClientAccount()" + LOG_CLOSE);
		id = account.getId();
		account = accountService.updateWithClientId(clientId, account);
		logger.info(LOG_TEXT + "ClientAccount was updated: " + account + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateClientAccount()" + LOG_CLOSE);
		return new ResponseEntity<>(account, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClientAccount(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
