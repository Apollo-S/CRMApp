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
public class ClientController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService service;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Client>> getAllClients() {
		logger.info(LOG_ENTER_METHOD + "getAllClients()" + LOG_CLOSE);
		List<Client> clients = service.getAll();
		if (clients.size() == 0) {
			logger.info(LOG_ERROR + "Clients were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of clients: " + clients.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllClients()" + LOG_CLOSE);
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Client> getClientById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getClientById()" + LOG_CLOSE);
		Client client = service.getById(id);
		if (client == null) {
			logger.info(LOG_ERROR + "Client with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "Client with ID=" + id + " was found: " + client + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getClientById()" + LOG_CLOSE);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		logger.info(LOG_ENTER_METHOD + "addClient()" + LOG_CLOSE);
		Client savedClient = service.save(client);
		logger.info(LOG_TEXT + "Client added with ID=" + savedClient.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addClient()" + LOG_CLOSE);
		return new ResponseEntity<Client>(savedClient, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Client> updateClient(@PathVariable(PARAM_ID) int id, @RequestBody Client client) {
		logger.info(LOG_ENTER_METHOD + "updateClient()" + LOG_CLOSE);
		client = service.update(id, client);
		logger.info(LOG_TEXT + "Client with ID=" + id + " was updated: " + client + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateClient()" + LOG_CLOSE);
		return new ResponseEntity<Client>(client, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClient(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteClient()" + LOG_CLOSE);
		service.delete(id);
		logger.info(LOG_TEXT + "Client with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteClient()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
