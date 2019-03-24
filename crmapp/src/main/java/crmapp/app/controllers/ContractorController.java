package crmapp.app.controllers;

import crmapp.app.entities.Contractor;
import crmapp.app.entities.ContractorType;
import crmapp.app.services.ContractorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ContractorController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ContractorController.class);

	@Autowired
	private ContractorService contractorService;

	@GetMapping(value = "/contractors", headers = HEADER_JSON)
	public ResponseEntity<List<Contractor>> getAllContractors() {
		logger.info(LOG_ENTER_METHOD + "getAllContractors()" + LOG_CLOSE);
		List<Contractor> contractors = contractorService.findAll();
		if (contractors == null) {
			logger.info(LOG_ERROR + "Contractors were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of contractors: " + contractors.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllContractors()" + LOG_CLOSE);
		return new ResponseEntity<>(contractors, HttpStatus.OK);
	}

//	@GetMapping(value = "/clients", headers = HEADER_JSON)
//	public ResponseEntity<List<Contractor>> getAllClients() {
//		logger.info(LOG_ENTER_METHOD + "getAllClients()" + LOG_CLOSE);
//		List<Contractor> clients = contractorService.findAllByContractorType(ContractorType.CLIENT);
//		if (clients == null) {
//			logger.info(LOG_ERROR + "Clients were not found" + LOG_CLOSE);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		logger.info(LOG_TEXT + "Count of clients: " + clients.size() + LOG_CLOSE);
//		logger.info(LOG_OUT_OF_METHOD + "getAllClients()" + LOG_CLOSE);
//		return new ResponseEntity<>(clients, HttpStatus.OK);
//	}

}
