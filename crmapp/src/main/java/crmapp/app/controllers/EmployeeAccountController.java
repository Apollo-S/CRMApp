package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.EmployeeAccount;
import crmapp.app.services.EmployeeAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/employees/{employeeId}/accounts")
public class EmployeeAccountController extends BaseController<EmployeeAccount, EmployeeAccountService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<EmployeeAccount>> getAllEmployeeAccountsByEmployeeId(
            @PathVariable("employeeId") Integer employeeId) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT); // TODO needed namedquery
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<EmployeeAccount> getEmployeeAccountById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<EmployeeAccount> addEmployeeAccount(@PathVariable("employeeId") Integer employeeId,
                                                              @RequestBody EmployeeAccount account) {
        return super.addEntity(account); // TODO needed namedquery
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updateEmployeeAccount(@PathVariable("employeeId") Integer employeeId,
                                                      @RequestBody EmployeeAccount account) {
        return super.updateEntity(account);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteEmployeeAccount(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
