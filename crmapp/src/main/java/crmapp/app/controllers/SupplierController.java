package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.Supplier;
import crmapp.app.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/suppliers")
public class SupplierController extends BaseController<Supplier, SupplierService> {

    @GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Supplier>> getAllSuppliers() {
		return super.getAllEntities();
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Supplier> getSupplierById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityBy(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
		return super.addEntity(supplier);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updateSupplier(@PathVariable(PARAM_ID) int id,
											   @RequestBody Supplier supplier) {
		return super.updateEntity(supplier);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteSupplier(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
