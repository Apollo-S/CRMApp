package crmapp.app.controllers;

import java.util.List;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.services.EmployeeService;
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

import crmapp.app.entities.Employee;
import crmapp.app.repositories.EmployeeRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/employees")
public class EmployeeController extends BaseController<Employee, EmployeeService> {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		if (employees.size() == 0) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(PARAM_ID) int id) {
		logger.info("<==/////////// Entering to the getEmployeeById() method ... ///////////==>");
		Employee employee = employeeRepository.findOne(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
		}
		logger.info("<==/////////// Printing employee: " + employee + "///////////==>");
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		logger.info("<==/////////// Entering to the addEmployee() method ... ///////////==>");
		employee.setVersion(0);
		employee = employeeRepository.save(employee);
		logger.info("<==/////////// Printing new employee: " + employee + "///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Employee>(employee, header, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Employee> updateEmployee(@PathVariable(PARAM_ID) int id, @RequestBody Employee employee) {
		logger.info("<==/////////// Entering to the updateEmployee() method ... ///////////==>");
		employee.setId(id);
		employee.setVersion(employeeRepository.getOne(id).getVersion());
		employee = employeeRepository.save(employee);
		logger.info("<==/////////// Printing updated employee: " + employee + "///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteEmployee(@PathVariable(PARAM_ID) int id) {
		logger.info("<==/////////// Entering to the deleteEmployee() method ... ///////////==>");
		employeeRepository.delete(id);
		logger.info("<==/////////// Deleted employee with ID=" + id + " ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
