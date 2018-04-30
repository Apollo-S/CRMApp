package crmapp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.Client;
import crmapp.app.entities.OurCompany;
import crmapp.app.repositories.ClientRepository;
import crmapp.app.repositories.OurCompanyRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/our-companies")
public class OurCompanyController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(OurCompanyController.class);

	@Autowired
	private OurCompanyRepository companyRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<OurCompany>> getAllOurCompanies() {
		logger.info(LOG_ENTER_METHOD + "getAllOurCompanies()" + LOG_CLOSE);
		List<OurCompany> companies = companyRepository.findAll();
		if (companies.size() == 0) {
			logger.info(LOG_ERROR + "OurCompanies were not found" + LOG_CLOSE);
			return new ResponseEntity<List<OurCompany>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of OurCompanies: " + companies.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllOurCompanies()" + LOG_CLOSE);
		return new ResponseEntity<List<OurCompany>>(companies, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompany> getOurCompanyById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getOurCompanyById()" + LOG_CLOSE);
		OurCompany company = companyRepository.findOne(id);
		if (company == null) {
			logger.info(LOG_ERROR + "OurCompany with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<OurCompany>(company, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "OurCompany with ID=" + id + " was found: " + company + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getOurCompanyById()" + LOG_CLOSE);
		return new ResponseEntity<OurCompany>(company, HttpStatus.OK);
	}

}