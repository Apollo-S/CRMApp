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
		logger.info(LOG_ENTER_METHOD + "getAllClients()" + LOG_CLOSE);
		ResponseEntity<List<Client>> responseEntity = super.getAllEntities();
		logger.info(LOG_OUT_OF_METHOD + "getAllClients()" + LOG_CLOSE);
		return responseEntity;
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Client> getClientById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getClientById()" + LOG_CLOSE);
		ResponseEntity<Client> responseEntity = super.getEntityById(id);
		logger.info(LOG_OUT_OF_METHOD + "getClientById()" + LOG_CLOSE);
		return responseEntity;
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		logger.info(LOG_ENTER_METHOD + "addClient()" + LOG_CLOSE);
		Client savedClient = super.service.save(client);
		logger.info(LOG_TEXT + "Client added with ID=" + savedClient.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addClient()" + LOG_CLOSE);
		return new ResponseEntity<>(savedClient, new HttpHeaders(), HttpStatus.CREATED);
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
		logger.info(LOG_ENTER_METHOD + "deleteClient()" + LOG_CLOSE);
		super.service.delete(id);
		logger.info(LOG_TEXT + "Client with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteClient()" + LOG_CLOSE);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
