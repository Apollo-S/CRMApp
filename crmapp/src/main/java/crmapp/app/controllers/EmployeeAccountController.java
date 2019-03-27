package crmapp.app.controllers;

import java.util.List;

import crmapp.app.services.EmployeeAccountService;
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
import crmapp.app.entities.EmployeeAccount;
import crmapp.app.repositories.ClientRepository;
import crmapp.app.repositories.EmployeeAccountRepository;
import crmapp.app.repositories.EmployeeRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/employees/{employeeId}/accounts")
public class EmployeeAccountController extends BaseController<EmployeeAccount, EmployeeAccountService> {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeAccountController.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeAccountRepository accountRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<EmployeeAccount>> getAllEmployeeAccountsByEmployeeId(
			@PathVariable("employeeId") Integer employeeId) {
		List<EmployeeAccount> accounts = accountRepository.findAllByEmployeeId(employeeId);
		if (accounts.size() == 0) {
			return new ResponseEntity<List<EmployeeAccount>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EmployeeAccount>>(accounts, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<EmployeeAccount> getEmployeeAccountById(@PathVariable(PARAM_ID) int id) {
		logger.info("<==/////////// Entering to the getEmployeeAccountById() method ... ///////////==>");
		EmployeeAccount account = accountRepository.findOne(id);
		if (account == null) {
			return new ResponseEntity<EmployeeAccount>(account, HttpStatus.NOT_FOUND);
		}
		logger.info("<==/////////// Printing EmployeeAccount: " + account + "///////////==>");
		return new ResponseEntity<EmployeeAccount>(account, HttpStatus.OK);
	}

	@PostMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<EmployeeAccount> addEmployeeAccount(@PathVariable("employeeId") Integer employeeId, 
			@RequestBody EmployeeAccount account) {
		logger.info("<==/////////// Entering to the addEmployeeAccount() method ... ///////////==>");
		Employee employee = employeeRepository.findOne(employeeId);
		logger.info("<==/////////// Getting the employee " + employee + " ///////////==>");
		account.setEmployee(employee);
		account.setVersion(0);
		account = accountRepository.save(account);
		logger.info("<==/////////// Saving of EmployeeAccount with ID = " + employee.getId()
			+ " was successfull ... ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<EmployeeAccount>(account, header, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<EmployeeAccount> updateEmployeeAccount(@PathVariable("employeeId") Integer employeeId, 
			@RequestBody EmployeeAccount account) {
		logger.info("<==/////////// Entering to the updateEmployeeAccount() method ... ///////////==>");
		Employee employee = employeeRepository.findOne(employeeId);
		account.setEmployee(employee);
		logger.info("<==/////////// Employee is setted to " + employee + "///////////==>");
		int actualVersionNumber = accountRepository.getOne(account.getId()).getVersion();
		account.setVersion(actualVersionNumber);
		account = accountRepository.save(account);
		logger.info("<==/////////// Printing account: " + account + "///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<EmployeeAccount>(account, header, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteEmployeeAccount(@PathVariable(PARAM_ID) int id) {
		accountRepository.delete(id);
		logger.info("<==/////////// EmployeeAccount  with ID = " + id + " was deleted successfully!!! ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
