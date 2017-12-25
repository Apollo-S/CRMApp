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
	public ResponseEntity<List<ClientDirector>> getAllClientDirectores(@PathVariable("clientId") Integer clientId) {
		List<ClientDirector> directors = directorRepository.findAllDirectorsByClientId(clientId);
		if (directors.size() == 0) {
			return new ResponseEntity<List<ClientDirector>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ClientDirector>>(directors, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> getClientDirectorById(@PathVariable(PARAM_ID) int id) {
		ClientDirector director = directorRepository.findOne(id);
		if (director == null) {
			return new ResponseEntity<ClientDirector>(director, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ClientDirector>(director, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> addClientDirector(@PathVariable("clientId") int clientId,
			@RequestBody ClientDirector director) {
		logger.info("<==/////////// Entering to the addClientDirector() method ... ///////////==>");
		Client client = clientRepository.findOne(clientId);
		logger.info("<==/////////// Getting the client with ID = " + client.getId() + " ///////////==>");
		director.setClient(client);
		director.setVersion(0);
		director = directorRepository.save(director);
		logger.info("<==/////////// Saving of ClientDirector with ID = " + director.getId()
				+ " was successfull ... ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<ClientDirector>(director, header, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> updateClientDirector(@PathVariable("clientId") int clientId, 
			@RequestBody ClientDirector director) {
		logger.info("<==/////////// Entering to the updateClientDirector() method ... ///////////==>");
		Client client = clientRepository.findOne(clientId);
		director.setClient(client);
		logger.info("<==/////////// Client is setted to " + client + "///////////==>");
		int actualVersionNumber = directorRepository.getOne(director.getId()).getVersion();
		director.setVersion(actualVersionNumber);
		logger.info("<==/////////// Printing director: " + director + "///////////==>");
		director = directorRepository.save(director);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<ClientDirector>(director, header, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClientDirector(@PathVariable(PARAM_ID) int id) {
		directorRepository.delete(id);
		logger.info("<==/////////// ClientDirector  with ID = " + id + " was deleted successfully!!! ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
