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

import crmapp.app.entities.Vacation;
import crmapp.app.repositories.VacationRepository;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class VacationController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(VacationController.class);
	
	@Autowired
	private VacationRepository vacationRepository;
	
	@GetMapping(value = "/employees/{employeeId}/vacations", headers = HEADER_JSON)
	public ResponseEntity<List<Vacation>> getAllVacationsByEmployeeId(@PathVariable("employeeId") int employeeId) {
		logger.info(LOG_ENTER_METHOD + "getAllVacationsByEmployeeId()" + LOG_CLOSE);
		List<Vacation> vacations = vacationRepository.findAllVacationsByEmployeeId(employeeId);
		if (vacations == null) {
			logger.info(LOG_ERROR + "Vacations were not found" + LOG_CLOSE);
			return new ResponseEntity<List<Vacation>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of Vacations: " + vacations.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllVacationsByEmployeeId()" + LOG_CLOSE);
		return new ResponseEntity<List<Vacation>>(vacations, HttpStatus.OK);
	}
	
	@GetMapping(value = "/vacations", headers = HEADER_JSON) 
	public ResponseEntity<List<Vacation>> getAllVacations() {
		logger.info(LOG_ENTER_METHOD + "getAllVacations()" + LOG_CLOSE);
		List<Vacation> vacations = vacationRepository.findAll();
		if(vacations.size() == 0) {
			logger.info(LOG_ERROR + "Vacations were not found" + LOG_CLOSE);
			return new ResponseEntity<List<Vacation>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of Vacations: " + vacations.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllVacations()" + LOG_CLOSE);
		return new ResponseEntity<List<Vacation>>(vacations, HttpStatus.OK);
	}

	@GetMapping(value = "/vacations/{id}", headers = HEADER_JSON)
	public ResponseEntity<Vacation> getVacationById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getVacationById()" + LOG_CLOSE);
		Vacation vacation = vacationRepository.findOne(id);
		if (vacation == null) {
			logger.info(LOG_ERROR + "Vacation with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<Vacation>(vacation, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "Vacation with ID=" + id + " was found: " + vacation + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getVacationById()" + LOG_CLOSE);
		return new ResponseEntity<Vacation>(vacation, HttpStatus.OK);
	}

	@PostMapping(value = "/vacations", headers = HEADER_JSON)
	public ResponseEntity<Vacation> addVacation(@RequestBody Vacation vacation) {
		logger.info(LOG_ENTER_METHOD + "addVacation()" + LOG_CLOSE);
		vacation.setVersion(0);
		vacation = vacationRepository.save(vacation);
		logger.info(LOG_TEXT + "Vacation added with ID=" + vacation.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addVacation()" + LOG_CLOSE);
		return new ResponseEntity<Vacation>(vacation, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/vacations/{id}", headers = HEADER_JSON)
	public ResponseEntity<Vacation> updateVacation(@PathVariable(PARAM_ID) int id,
			@RequestBody Vacation vacation) {
		logger.info(LOG_ENTER_METHOD + "updateVacation()" + LOG_CLOSE);
		vacation.setId(id);
		int actualVersionNumber = vacationRepository.getOne(id).getVersion();
		vacation.setVersion(actualVersionNumber);
		vacation = vacationRepository.save(vacation);
		logger.info(LOG_TEXT + "Vacation with ID=" + id + " was updated: " + vacation + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateVacation()" + LOG_CLOSE);
		return new ResponseEntity<Vacation>(vacation, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/vacations/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteVacation(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deleteVacation()" + LOG_CLOSE);
		vacationRepository.delete(id);
		logger.info(LOG_TEXT + "Vacation with ID=" + id + " was deleted" + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteVacation()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
