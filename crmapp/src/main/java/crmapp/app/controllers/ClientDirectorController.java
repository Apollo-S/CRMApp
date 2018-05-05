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
import crmapp.app.entities.ClientDirector;
import crmapp.app.repositories.ClientDirectorRepository;
import crmapp.app.repositories.ClientRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/clients/{clientId}/directors")
public class ClientDirectorController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ClientDirectorController.class);

	@Autowired
	private ClientDirectorRepository directorRepository;

	@Autowired
	private ClientRepository clientRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<ClientDirector>> getAllClientDirectors(@PathVariable("clientId") Integer clientId) {
		logger.info(LOG_ENTER_METHOD + "getAllClientDirectors()" + LOG_CLOSE);
		List<ClientDirector> directors = directorRepository.findAllDirectorsByClientId(clientId);
		if (directors.size() == 0) {
			logger.info(LOG_ERROR + "ClientDirectors were not found" + LOG_CLOSE);
			return new ResponseEntity<List<ClientDirector>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of ClientDirectors: " + directors.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllClientDirectors()" + LOG_CLOSE);
		return new ResponseEntity<List<ClientDirector>>(directors, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> getClientDirectorById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getClientDirectorById()" + LOG_CLOSE);
		ClientDirector director = directorRepository.findOne(id);
		if (director == null) {
			logger.info(LOG_ERROR + "ClientDirector with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<ClientDirector>(director, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "ClientDirector with ID=" + id + " was found: " + director + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getClientDirectorById()" + LOG_CLOSE);
		return new ResponseEntity<ClientDirector>(director, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> addClientDirector(@PathVariable("clientId") int clientId,
			@RequestBody ClientDirector director) {
		logger.info(LOG_ENTER_METHOD + "addClientDirector()" + LOG_CLOSE);
		Client client = clientRepository.findOne(clientId);
		logger.info(LOG_TEXT + "Obtained Client with ID = " + client.getId() + LOG_CLOSE);
		director.setClient(client);
		director.setVersion(0);
		director = directorRepository.save(director);
		logger.info(LOG_TEXT + "ClientDirector added with ID=" + director.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addClientDirector()" + LOG_CLOSE);
		return new ResponseEntity<ClientDirector>(director, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> updateClientDirector(@PathVariable("clientId") int clientId, 
			@RequestBody ClientDirector director) {
		logger.info(LOG_ENTER_METHOD + "updateClientDirector()" + LOG_CLOSE);
		Client client = clientRepository.findOne(clientId);
		director.setClient(client);
		logger.info(LOG_TEXT + "Client is setted to " + client + LOG_CLOSE);
		int actualVersionNumber = directorRepository.getOne(director.getId()).getVersion();
		director.setVersion(actualVersionNumber);
		director = directorRepository.save(director);
		logger.info(LOG_TEXT + "ClientDirector was updated: " + director + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateClientDirector()" + LOG_CLOSE);
		return new ResponseEntity<ClientDirector>(director, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClientDirector(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteClientDirector()" + LOG_CLOSE);
		directorRepository.delete(id);
		logger.info(LOG_TEXT + "ClientDirector with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteClientDirector()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
