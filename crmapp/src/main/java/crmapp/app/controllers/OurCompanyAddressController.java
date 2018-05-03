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
import crmapp.app.entities.OurCompany;
import crmapp.app.entities.OurCompanyAddress;
import crmapp.app.repositories.OurCompanyAddressRepository;
import crmapp.app.repositories.OurCompanyRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/our-companies/{companyId}/addresses")
public class OurCompanyAddressController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(OurCompanyAddressController.class);

	@Autowired
	private OurCompanyAddressRepository addressRepository;

	@Autowired
	private OurCompanyRepository companyRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<OurCompanyAddress>> getAllOurCompanyAddresses(
			@PathVariable("companyId") Integer companyId) {
		logger.info(LOG_ENTER_METHOD + "getAllOurCompanyAddresses()" + LOG_CLOSE);
		List<OurCompanyAddress> addresses = addressRepository.findAllOurCompanyAddressesByCompanyId(companyId);
		if (addresses.size() == 0) {
			logger.info(LOG_ERROR + "OurCompanyAddresses were not found" + LOG_CLOSE);
			return new ResponseEntity<List<OurCompanyAddress>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of OurCompanyAddresses: " + addresses.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllOurCompanyAddresses()" + LOG_CLOSE);
		return new ResponseEntity<List<OurCompanyAddress>>(addresses, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAddress> getOurCompanyAddressById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getOurCompanyAddressById()" + LOG_CLOSE);
		OurCompanyAddress address = addressRepository.findOne(id);
		if (address == null) {
			logger.info(LOG_ERROR + "OurCompanyAccount with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<OurCompanyAddress>(address, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_OUT_OF_METHOD + "getOurCompanyAddressById()" + LOG_CLOSE);
		return new ResponseEntity<OurCompanyAddress>(address, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAddress> addOurCompanyAddress(@PathVariable("companyId") int companyId,
			@RequestBody OurCompanyAddress address) {
		logger.info(LOG_ENTER_METHOD + "addOurCompanyAddress()" + LOG_CLOSE);
		OurCompany company = companyRepository.findOne(companyId);
		logger.info(LOG_TEXT + "Obtained OurCompany with ID = " + company.getId() + LOG_CLOSE);
		address.setOurCompany(company);
		address.setVersion(0);
		address = addressRepository.save(address);
		logger.info(LOG_TEXT + "OurCompanyAddress added with ID=" + address.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addOurCompanyAddress()" + LOG_CLOSE);
		return new ResponseEntity<OurCompanyAddress>(address, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyAddress> updateOurCompanyAddress(@PathVariable("companyId") int companyId,
			@RequestBody OurCompanyAddress address) {
		logger.info(LOG_ENTER_METHOD + "updateOurCompanyAddress()" + LOG_CLOSE);
		OurCompany company = companyRepository.findOne(companyId);
		address.setOurCompany(company);
		logger.info(LOG_TEXT + "OurCompany is setted to " + company + LOG_CLOSE);
		int actualVersionNumber = addressRepository.getOne(address.getId()).getVersion();
		address.setVersion(actualVersionNumber);
		address = addressRepository.save(address);
		logger.info(LOG_TEXT + "OurCompanyAddress was updated: " + address + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateOurCompanyAddress()" + LOG_CLOSE);
		return new ResponseEntity<OurCompanyAddress>(address, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteOurCompanyAddress(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteOurCompanyAddress()" + LOG_CLOSE);
		addressRepository.delete(id);
		logger.info(LOG_TEXT + "OurCompanyAccount with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteOurCompanyAddress()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}