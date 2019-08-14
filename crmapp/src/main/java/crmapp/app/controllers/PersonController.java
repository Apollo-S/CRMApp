package crmapp.app.controllers;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.entities.Person;
import crmapp.app.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping(value = "/api/persons")
public class PersonController extends BaseController<Person, PersonService> {

    @GetMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<List<Person>> getAllPersons() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Person> getPersonById(@PathVariable(PARAM_ID) int id) {
        return super.getEntityBy(id);
    }

    @PostMapping(value = "", headers = HEADER_JSON)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return super.addEntity(person);
    }

    @PutMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> updatePerson(@PathVariable(PARAM_ID) int id, @RequestBody Person person) {
        return super.updateEntity(id, person);
    }

    @DeleteMapping(value = "/{id}", headers = HEADER_JSON)
    public ResponseEntity<Void> deletePerson(@PathVariable(PARAM_ID) int id) {
        return super.deleteEntityById(id);
    }

}
