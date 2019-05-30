package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.Contractor;
import crmapp.app.services.ContractorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/contractors")
public class ContractorController extends BaseController<Contractor, ContractorService> {

	private static final Logger logger = LoggerFactory.getLogger(ContractorController.class);

	@GetMapping(value = "/{contractorType}", headers = HEADER_JSON)
	public ResponseEntity<List<Contractor>> getAllContractorsByType(@PathVariable("contractorType") String contractorType) {
		logger.info(LOG_ENTER_METHOD + "getAllContractors()" + LOG_CLOSE);
		logger.info(LOG_TEXT + "contractorType = '" + contractorType + "'" + LOG_CLOSE);
		List<Contractor> contractors = super.service.findAllByContractorType(contractorType);
		if (contractors == null) {
			logger.info(LOG_ERROR + "Contractors were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of contractors: " + contractors.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllContractors()" + LOG_CLOSE);
		return new ResponseEntity<>(contractors, HttpStatus.OK);
	}

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Contractor>> getAllContractors() {
		logger.info(LOG_ENTER_METHOD + "getAllContractors()" + LOG_CLOSE);
		ResponseEntity<List<Contractor>> responseEntity = super.getAllEntities();
		logger.info(LOG_OUT_OF_METHOD + "getAllContractors()" + LOG_CLOSE);
		return responseEntity;
	}

}
