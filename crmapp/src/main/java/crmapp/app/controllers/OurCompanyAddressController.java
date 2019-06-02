package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.OurCompanyAddress;
import crmapp.app.services.OurCompanyAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/our-companies/{id}/addresses")
public class OurCompanyAddressController extends ExtendedBaseController<OurCompanyAddress, OurCompanyAddressService> {

	private static final Logger logger = LoggerFactory.getLogger(OurCompanyAddressController.class);
	private final OurCompanyAddressService addressService;

	@Autowired
	public OurCompanyAddressController(OurCompanyAddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<OurCompanyAddress>> getAllByOurCompanyId(@PathVariable("id") Integer id) {
		return super.getAllFilterBy("ourCompany", id);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAddress> getOurCompanyAddressById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityById(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAddress> addOurCompanyAddress(@PathVariable("clientId") int clientId,
														  @RequestBody OurCompanyAddress address) {
		logger.info(LOG_ENTER_METHOD + "addOurCompanyAddress()" + LOG_CLOSE);
		OurCompanyAddress savedAddress = super.service.save(clientId, address);
		logger.info(LOG_TEXT + "OurCompanyAddress added with ID=" + savedAddress.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addOurCompanyAddress()" + LOG_CLOSE);
		return new ResponseEntity<>(savedAddress, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAddress> updateOurCompanyAddress(@PathVariable("clientId") int clientId,
															 @RequestBody OurCompanyAddress address) {
		logger.info(LOG_ENTER_METHOD + "updateOurCompanyAddress()" + LOG_CLOSE);
		OurCompanyAddress updatedAddress = addressService.updateWithCompanyId(clientId, address);
		logger.info(LOG_TEXT + "OurCompanyAddress was updated: " + updatedAddress + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateOurCompanyAddress()" + LOG_CLOSE);
		return new ResponseEntity<>(updatedAddress, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteOurCompanyAddress(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
