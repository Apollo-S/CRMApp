package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.Vacation;
import crmapp.app.services.VacationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class VacationController extends BaseController<Vacation, VacationService> {

    @GetMapping(value = "/employees/{employeeId}/vacations", headers = HEADER_JSON)
    public ResponseEntity<List<Vacation>> getAllVacationsByEmployeeId(@PathVariable("employeeId") int employeeId) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT); //TODO Needed NamedQuery
    }

    @GetMapping(value = "/vacations", headers = HEADER_JSON)
    public ResponseEntity<List<Vacation>> getAllVacations() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/vacations/{id}", headers = HEADER_JSON)
    public ResponseEntity<Vacation> getVacationById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "/vacations", headers = HEADER_JSON)
    public ResponseEntity<Vacation> addVacation(@RequestBody Vacation vacation) {
        return super.addEntity(vacation);
    }

    @PutMapping(value = "/vacations/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updateVacation(@PathVariable(PARAM_ID) int id,
                                               @RequestBody Vacation vacation) {
        return super.updateEntity(id, vacation);
    }

    @DeleteMapping(value = "/vacations/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deleteVacation(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
