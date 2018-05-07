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
import crmapp.app.repositories.ClientRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/clients")
public class ClientController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ClientRepository clientRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Client>> getAllClients() {
		logger.info(LOG_ENTER_METHOD + "getAllClients()" + LOG_CLOSE);
		List<Client> clients = clientRepository.findAll();
		if (clients.size() == 0) {
			logger.info(LOG_ERROR + "Clients were not found" + LOG_CLOSE);
			return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of clients: " + clients.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllClients()" + LOG_CLOSE);
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Client> getClientById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getClientById()" + LOG_CLOSE);
		Client client = clientRepository.findOne(id);
		if (client == null) {
			logger.info(LOG_ERROR + "Client with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<Client>(client, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "Client with ID=" + id + " was found: " + client + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getClientById()" + LOG_CLOSE);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		logger.info(LOG_ENTER_METHOD + "addClient()" + LOG_CLOSE);
		client.setVersion(0);
		client = clientRepository.save(client);
		logger.info(LOG_TEXT + "Client added with ID=" + client.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addClient()" + LOG_CLOSE);
		return new ResponseEntity<Client>(client, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Client> updateClient(@PathVariable(PARAM_ID) int id, @RequestBody Client client) {
		logger.info(LOG_ENTER_METHOD + "updateClient()" + LOG_CLOSE);
		client.setId(id);
		int actualVersionNumber = clientRepository.getOne(id).getVersion();
		client.setVersion(actualVersionNumber);
		client = clientRepository.save(client);
		logger.info(LOG_TEXT + "Client with ID=" + id + " was updated: " + client + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateClient()" + LOG_CLOSE);
		return new ResponseEntity<Client>(client, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClient(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteClient()" + LOG_CLOSE);
		clientRepository.delete(id);
		logger.info(LOG_TEXT + "Client with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteClient()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}