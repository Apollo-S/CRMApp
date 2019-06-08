package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.ClientDirector;
import crmapp.app.services.ClientDirectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clients/{clientId}/directors")
public class ClientDirectorController extends ExtendedBaseController<ClientDirector, ClientDirectorService> {

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<ClientDirector>> getAllClientDirectors(@PathVariable("clientId") Integer clientId) {
		return super.getAllFilterBy("client", clientId);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> getClientDirectorById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityBy(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> addClientDirector(@PathVariable("clientId") int clientId,
			@RequestBody ClientDirector director) {
		return super.addEntity(director);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<ClientDirector> updateClientDirector(@PathVariable("clientId") int clientId, 
			@RequestBody ClientDirector director) {
		return super.updateEntity(director);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteClientDirector(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
