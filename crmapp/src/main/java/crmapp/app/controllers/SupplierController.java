package crmapp.app.controllers;

import crmapp.app.entities.Supplier;
import crmapp.app.services.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/suppliers")
public class SupplierController extends BaseController<Supplier, SupplierService> {

	private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Supplier>> getAllSuppliers() {
		return super.getAllEntities();
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Supplier> getSupplierById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityById(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
		return super.addEntity(supplier);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Supplier> updateEntity(@PathVariable(PARAM_ID) int id,
											   @RequestBody Supplier supplier) {
		logger.info(LOG_ENTER_METHOD + "updateEntity()" + LOG_CLOSE);
		supplier = super.service.update(id, supplier);
		logger.info(LOG_TEXT + "Supplier with ID=" + id + " was updated: " + supplier + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateEntity()" + LOG_CLOSE);
		return new ResponseEntity<>(supplier, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteSupplier(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
