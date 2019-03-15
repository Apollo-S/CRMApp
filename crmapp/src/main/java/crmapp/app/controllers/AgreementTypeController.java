package crmapp.app.controllers;

import crmapp.app.entities.AgreementType;
import crmapp.app.services.AgreementTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/agreement-types")
public class AgreementTypeController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AgreementTypeController.class);

	@Autowired
	private AgreementTypeService agrTypeService;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<AgreementType>> getAllAgreementTypes() {
		logger.info(LOG_ENTER_METHOD + "getAllAgreementTypes()" + LOG_CLOSE);
		List<AgreementType> agrTypes = agrTypeService.findAll();
		if (agrTypes.size() == 0) {
			logger.info(LOG_ERROR + "docTypes were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of docTypes equals " + agrTypes.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllAgreementTypes()" + LOG_CLOSE);
		return new ResponseEntity<>(agrTypes, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<AgreementType> getAgreementTypeById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getAgreementTypeById()" + LOG_CLOSE);
		AgreementType agrType = agrTypeService.findById(id);
		if (agrType == null) {
			logger.info(LOG_ERROR + "AgreementType with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "AgreementType with ID=" + id + " was found: " + agrType + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAgreementTypeById()" + LOG_CLOSE);
		return new ResponseEntity<>(agrType, HttpStatus.OK);
	}

}
