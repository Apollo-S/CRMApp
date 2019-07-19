package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.OurCompanyAddress;
import crmapp.app.services.OurCompanyAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/our-companies/{id}/addresses")
public class OurCompanyAddressController extends ExtendedBaseController<OurCompanyAddress, OurCompanyAddressService> {

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<OurCompanyAddress>> getAllByOurCompanyId(@PathVariable("id") Integer id) {
		return super.getAllFilterBy("ourCompany", id);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAddress> getOurCompanyAddressById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityBy(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAddress> addOurCompanyAddress(@PathVariable("clientId") int clientId,
														  @RequestBody OurCompanyAddress address) {
		return super.addEntity(address);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updateOurCompanyAddress(@PathVariable("clientId") int clientId,
															 @RequestBody OurCompanyAddress address) {
		return super.updateEntity(address);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteOurCompanyAddress(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
