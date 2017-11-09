package crmapp.app.controllers;

import java.util.List;

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
@RequestMapping(value = "/api/employees")
public class VacationController extends BaseController {
	
	@Autowired
	private VacationRepository vacationRepository;
	
	@GetMapping(value = "/{employeeId}/vacations", headers = HEADER_JSON)
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
		Vacation vacation = vacationRepository.findOne(id);
		if (vacation == null) {
			return new ResponseEntity<Vacation>(vacation, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vacation>(vacation, HttpStatus.OK);
	}

}
