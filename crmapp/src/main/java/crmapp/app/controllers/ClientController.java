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

import crmapp.app.entities.Client;
import crmapp.app.services.ClientService;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController extends BaseController<Client, ClientService> {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Client>> getAllClients() {
		return super.getAllEntities();
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Client> getClientById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityById(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		return super.addEntity(client);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Client> updateClient(@PathVariable(PARAM_ID) int id,
											   @RequestBody Client client) {
		logger.info(LOG_ENTER_METHOD + "updateClient()" + LOG_CLOSE);
		client = super.service.update(id, client);
		logger.info(LOG_TEXT + "Client with ID=" + id + " was updated: " + client + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateClient()" + LOG_CLOSE);
		return new ResponseEntity<>(client, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClient(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
