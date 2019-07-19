package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.Employee;
import crmapp.app.entities.dto.EmployeeDTO;
import crmapp.app.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController extends BaseController<Employee, EmployeeService> {

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
		return super.getAllEntities(EmployeeDTO.class);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(PARAM_ID) int id) {
		return super.getEntityBy(id);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		return super.addEntity(employee);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updateEmployee(@PathVariable(PARAM_ID) int id, @RequestBody Employee employee) {
		return super.updateEntity(employee);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteEmployee(@PathVariable(PARAM_ID) int id) {
		return super.deleteEntityById(id);
	}

}
