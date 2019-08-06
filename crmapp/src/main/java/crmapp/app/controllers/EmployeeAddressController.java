package crmapp.app.controllers;

import crmapp.app.controllers.base.ExtendedBaseController;
import crmapp.app.entities.EmployeeAddress;
import crmapp.app.entities.dto.EmployeeAddressDTO;
import crmapp.app.services.EmployeeAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static crmapp.app.entities.EmployeeAddress.FIND_ALL_ADDRESSES_BY_EMPLOYEE_ID;

@RestController
@RequestMapping(value = "/api/employees/{employeeId}/addresses")
public class EmployeeAddressController extends ExtendedBaseController<EmployeeAddress, EmployeeAddressService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<EmployeeAddressDTO>> getAllEmployeeAddressesByEmployeeId(
            @PathVariable("employeeId") Integer employeeId) {
        return super.getAllByNamedQuery(FIND_ALL_ADDRESSES_BY_EMPLOYEE_ID, EmployeeAddressDTO.class, employeeId);
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<EmployeeAddressDTO> getEmployeeAddressById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id, EmployeeAddressDTO.class);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<EmployeeAddressDTO> addEmployeeAddress(@PathVariable("employeeId") int employeeId,
                                                        @RequestBody EmployeeAddressDTO address) {
        return super.addEntity(address);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updateEmployeeAddress(@PathVariable("employeeId") int employeeId,
                                                   @RequestBody EmployeeAddressDTO address) {
        return super.updateEntity(address);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteEmployeeAddress(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
