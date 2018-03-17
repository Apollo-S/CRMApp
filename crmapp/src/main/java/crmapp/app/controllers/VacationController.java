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
		List<Vacation> vacations = vacationRepository.findAllVacationsByEmployeeId(employeeId);
		if (vacations == null) {
			return new ResponseEntity<List<Vacation>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vacation>>(vacations, HttpStatus.OK);
	}
	
	@GetMapping(value = "/vacations", headers = HEADER_JSON) 
	public ResponseEntity<List<Vacation>> getAllVacations() {
		List<Vacation> vacations = vacationRepository.findAll();
		if(vacations.size() == 0) {
			return new ResponseEntity<List<Vacation>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vacation>>(vacations, HttpStatus.OK);
	}

	@GetMapping(value = "/vacations/{id}", headers = HEADER_JSON)
	public ResponseEntity<Vacation> getVacationById(@PathVariable(PARAM_ID) int id) {
		logger.info("<==/////////// Entering to the getVacationById() method ... ///////////==>");
		Vacation vacation = vacationRepository.findOne(id);
		if (vacation == null) {
			return new ResponseEntity<Vacation>(vacation, HttpStatus.NOT_FOUND);
		}
		logger.info("<==/////////// Getting the vacation with ID = " + vacation.getId() + " ///////////==>");
		logger.info("<==/////////// Printing vacation: " + vacation + "///////////==>");
		return new ResponseEntity<Vacation>(vacation, HttpStatus.OK);
	}

}
