package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.OurCompanyAccount;
import crmapp.app.services.OurCompanyAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping(value = "/api/our-companies/{companyId}/accounts")
public class OurCompanyAccountController extends ExtendedBaseController<OurCompanyAccount, OurCompanyAccountService> {

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<OurCompanyAccount>> getAllOurCompanyAccountsByCompanyId(
			@PathVariable("companyId") int companyId) {
		return super.getAllFilterBy("ourCompany", companyId);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAccount> getOurCompanyAccountById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityBy(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAccount> addOurCompanyAccount(@PathVariable("companyId") int companyId,
			@RequestBody OurCompanyAccount account) {
		return super.addEntity(account);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAccount> updateOurCompanyAccount(@PathVariable("companyId") int companyId,
			@RequestBody OurCompanyAccount account) {
		return super.updateEntity(account);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteOurCompanyAccount(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
