package crmapp.app.controllers;

import java.util.List;

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
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping(value = REQUEST_MAPPING_EMPTY, headers = HEADER_JSON)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		if (employees.size() == 0) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@GetMapping(value = REQUEST_MAPPING_BY_ID, headers = HEADER_JSON)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(PARAM_ID) int id) {
		Employee employee = employeeRepository.findOne(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@PostMapping(value = REQUEST_MAPPING_EMPTY, headers = HEADER_JSON)
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
		employee.setVersion(0);
		employee = employeeRepository.save(employee);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@PutMapping(value = REQUEST_MAPPING_BY_ID, headers = HEADER_JSON)
	public ResponseEntity<Void> updateEmployee(@PathVariable(PARAM_ID) int id, @RequestBody Employee employee) {
		employee.setId(id);
		employee.setVersion(employeeRepository.getOne(id).getVersion());
		employee = employeeRepository.save(employee);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}

	@DeleteMapping(value = REQUEST_MAPPING_BY_ID, headers = HEADER_JSON)
	public ResponseEntity<Void> deleteEmployee(@PathVariable(PARAM_ID) int id) {
		employeeRepository.delete(id);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
