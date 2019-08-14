package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.MailOutput;
import crmapp.app.services.MailOutputService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mail-outputs")
public class MailOutputController extends BaseController<MailOutput, MailOutputService> {

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<MailOutput>> getAllMailOutputs() {
		return super.getAllEntities();
	}
	
	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<MailOutput> getMailOutputById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityBy(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<MailOutput> addMailOutput(@RequestBody MailOutput output) {
		return super.addEntity(output);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updateMailOutput(@PathVariable(PARAM_ID) int id,
			@RequestBody MailOutput output) {
		return super.updateEntity(id, output);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteMailOutput(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
