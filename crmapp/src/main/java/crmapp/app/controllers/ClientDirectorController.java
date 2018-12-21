package crmapp.app.controllers;

import java.util.List;

import crmapp.app.services.ClientDirectorService;
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

import crmapp.app.entities.ClientDirector;

@RestController
@RequestMapping(value = "/api/clients/{clientId}/directors")
public class ClientDirectorController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ClientDirectorController.class);

	@Autowired
	private ClientDirectorService directorService;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<ClientDirector>> getAllClientDirectors(@PathVariable("clientId") Integer clientId) {
		logger.info(LOG_ENTER_METHOD + "getAllClientDirectors()" + LOG_CLOSE);
		List<ClientDirector> directors = directorService.findAllByClientId(clientId);
		if (directors.size() == 0) {
			logger.info(LOG_ERROR + "ClientDirectors were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of ClientDirectors: " + directors.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllClientDirectors()" + LOG_CLOSE);
		return new ResponseEntity<>(directors, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> getClientDirectorById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getClientDirectorById()" + LOG_CLOSE);
		ClientDirector director = directorService.findById(id);
		if (director == null) {
			logger.info(LOG_ERROR + "ClientDirector with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "ClientDirector with ID=" + id + " was found: " + director + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getClientDirectorById()" + LOG_CLOSE);
		return new ResponseEntity<>(director, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> addClientDirector(@PathVariable("clientId") int clientId,
			@RequestBody ClientDirector director) {
		logger.info(LOG_ENTER_METHOD + "addClientDirector()" + LOG_CLOSE);
		ClientDirector savedDirector = directorService.save(clientId, director);
		logger.info(LOG_TEXT + "ClientDirector added with ID=" + savedDirector.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addClientDirector()" + LOG_CLOSE);
		return new ResponseEntity<>(savedDirector, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> updateClientDirector(@PathVariable("clientId") int clientId, 
			@RequestBody ClientDirector director) {
		logger.info(LOG_ENTER_METHOD + "updateClientDirector()" + LOG_CLOSE);
		ClientDirector updatedDirector = directorService.updateWithClientId(clientId, director);
		logger.info(LOG_TEXT + "ClientDirector was updated: " + updatedDirector + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateClientDirector()" + LOG_CLOSE);
		return new ResponseEntity<>(updatedDirector, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClientDirector(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteClientDirector()" + LOG_CLOSE);
		directorService.delete(id);
		logger.info(LOG_TEXT + "ClientDirector with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteClientDirector()" + LOG_CLOSE);
		return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
