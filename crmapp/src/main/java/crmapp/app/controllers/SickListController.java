package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.SickList;
import crmapp.app.services.SickListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
public class SickListController extends BaseController<SickList, SickListService> {

    @GetMapping(value = "/{employeeId}/sicklists", headers = HEADER_JSON)
    public ResponseEntity<List<SickList>> getAllSickListsByEmployeeId(@PathVariable("employeeId") int employeeId) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT); // TODO NamedQuery needed
    }

    @GetMapping(value = "/sicklists", headers = HEADER_JSON)
    public ResponseEntity<List<SickList>> getAllSickLists() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/sicklists/{id}", headers = HEADER_JSON)
    public ResponseEntity<SickList> getSickListById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

}
