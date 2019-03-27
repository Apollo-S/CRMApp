package crmapp.app.controllers;

import java.util.List;

import crmapp.app.services.OurCompanyDirectorService;
import crmapp.app.services.OurCompanyService;
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
import crmapp.app.entities.OurCompanyDirector;
import crmapp.app.repositories.OurCompanyDirectorRepository;
import crmapp.app.repositories.OurCompanyRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/our-companies/{companyId}/directors")
public class OurCompanyDirectorController extends BaseController<OurCompanyDirector, OurCompanyDirectorService> {

	private static final Logger logger = LoggerFactory.getLogger(OurCompanyDirectorController.class);

	@Autowired
	private OurCompanyDirectorRepository directorRepository;

	@Autowired
	private OurCompanyRepository companyRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<OurCompanyDirector>> getAllOurCompanyDirectors(@PathVariable("companyId") Integer companyId) {
		logger.info(LOG_ENTER_METHOD + "getAllOurCompanyDirectors()" + LOG_CLOSE);
		List<OurCompanyDirector> directors = directorRepository.findAllOurCompanyDirectorsByCompanyId(companyId);
		if (directors.size() == 0) {
			logger.info(LOG_ERROR + "OurCompanyDirectors were not found" + LOG_CLOSE);
			return new ResponseEntity<List<OurCompanyDirector>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of OurCompanyDirectors: " + directors.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllOurCompanyDirectors()" + LOG_CLOSE);
		return new ResponseEntity<List<OurCompanyDirector>>(directors, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyDirector> getOurCompanyDirectorById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getOurCompanyById()" + LOG_CLOSE);
		OurCompanyDirector director = directorRepository.findOne(id);
		if (director == null) {
			logger.info(LOG_ERROR + "OurCompanyDirector with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<OurCompanyDirector>(director, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "OurCompanyDirector with ID=" + id + " was found: " + director + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getOurCompanyDirectorById()" + LOG_CLOSE);
		return new ResponseEntity<OurCompanyDirector>(director, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyDirector> addOurCompanyDirector(@PathVariable("companyId") int companyId,
			@RequestBody OurCompanyDirector director) {
		logger.info(LOG_ENTER_METHOD + "addOurCompanyDirector()" + LOG_CLOSE);
		OurCompany company = companyRepository.findOne(companyId);
		logger.info(LOG_TEXT + "Obtained OurCompany with ID = " + company.getId() + LOG_CLOSE);
		director.setOurCompany(company);
		director.setVersion(0);
		director = directorRepository.save(director);
		logger.info(LOG_TEXT + "OurCompanyDirector added with ID=" + director.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addOurCompanyDirector()" + LOG_CLOSE);
		return new ResponseEntity<OurCompanyDirector>(director, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<OurCompanyDirector> updateOurCompanyDirector(@PathVariable("companyId") int companyId, 
			@RequestBody OurCompanyDirector director) {
		logger.info(LOG_ENTER_METHOD + "updateOurCompanyDirector()" + LOG_CLOSE);
		OurCompany company = companyRepository.findOne(companyId);
		director.setOurCompany(company);
		logger.info(LOG_TEXT + "OurCompany is setted to " + company + LOG_CLOSE);
		int actualVersionNumber = directorRepository.getOne(director.getId()).getVersion();
		director.setVersion(actualVersionNumber);
		director = directorRepository.save(director);
		logger.info(LOG_TEXT + "OurCompanyDirector was updated: " + director + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateOurCompanyDirector()" + LOG_CLOSE);
		return new ResponseEntity<OurCompanyDirector>(director, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteOurCompanyDirector(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteOurCompanyDirector()" + LOG_CLOSE);
		directorRepository.delete(id);
		logger.info(LOG_TEXT + "OurCompanyDirector with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteOurCompanyDirector()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}