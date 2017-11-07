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

import crmapp.app.entities.EmployeeAccount;
import crmapp.app.repositories.EmployeeAccountRepository;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class EmployeeAccountController extends BaseController {

	@Autowired
	private EmployeeAccountRepository accountRepository;

	@GetMapping(value = "/employees/{employeeId}/accounts", headers = HEADER_JSON)
	public ResponseEntity<List<EmployeeAccount>> getAllEmployeeAccountsByEmployeeId(
			@PathVariable("employeeId") Integer employeeId) {
		List<EmployeeAccount> accounts = accountRepository.findAllByEmployeeId(employeeId);
		if (accounts.size() == 0) {
			return new ResponseEntity<List<EmployeeAccount>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EmployeeAccount>>(accounts, HttpStatus.OK);
	}
	
	@GetMapping(value = "/employees/accounts", headers = HEADER_JSON)
	public ResponseEntity<List<EmployeeAccount>> getAllEmployeeAccounts() {
		List<EmployeeAccount> accounts = accountRepository.findAll();
		if (accounts.size() == 0) {
			return new ResponseEntity<List<EmployeeAccount>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<EmployeeAccount>>(accounts, HttpStatus.OK);
	}

	@GetMapping(value = "/employees/accounts/{id}", headers = HEADER_JSON)
	public ResponseEntity<EmployeeAccount> getEmployeeAccountById(@PathVariable(PARAM_ID) int id) {
		EmployeeAccount account = accountRepository.findOne(id);
		if (account == null) {
			return new ResponseEntity<EmployeeAccount>(account, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EmployeeAccount>(account, HttpStatus.OK);
	}

	@PostMapping(value = "/employees/accounts/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> addEmployeeAccount(@RequestBody EmployeeAccount account) {
		account.setVersion(0);
		account = accountRepository.save(account);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/employees/accounts/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> updateEmployeeAccount(@PathVariable(PARAM_ID) int id,
			@RequestBody EmployeeAccount account) {
		account.setId(id);
		account.setVersion(accountRepository.getOne(id).getVersion());
		account = accountRepository.save(account);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/employees/accounts/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteEmployee(@PathVariable(PARAM_ID) int id) {
		accountRepository.delete(id);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
