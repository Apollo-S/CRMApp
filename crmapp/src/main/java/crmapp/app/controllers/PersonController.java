package crmapp.app.controllers;

import java.util.List;

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

import crmapp.app.entities.Person;
import crmapp.app.repositories.PersonRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/persons")
public class PersonController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonRepository personRepository;

	@GetMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<List<Person>> getAllPersons() {
		logger.info(LOG_ENTER_METHOD + "getAllPersons()" + LOG_CLOSE);
		List<Person> persons = personRepository.findAll();
		if (persons.size() == 0) {
			logger.info(LOG_ERROR + "No Person was found" + LOG_CLOSE);
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of persons: " + persons.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllPersons()" + LOG_CLOSE);
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Person> getPersonById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getPersonById()" + LOG_CLOSE);
		Person person = personRepository.findOne(id);
		if (person == null) {
			logger.info(LOG_ERROR + "Person wasn't found with ID=" + id + LOG_CLOSE);
			return new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "Person with ID=" + id + " was found: " + person + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getPersonById()" + LOG_CLOSE);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		logger.info(LOG_ENTER_METHOD + "addPerson()" + LOG_CLOSE);
		person.setVersion(0);
		person = personRepository.save(person);
		logger.info(LOG_TEXT + "Person added  with ID=" + person.getId() + LOG_CLOSE);
		HttpHeaders header = new HttpHeaders();
		logger.info(LOG_OUT_OF_METHOD + "addPerson()" + LOG_CLOSE);
		return new ResponseEntity<Person>(person, header, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Person> updatePerson(@PathVariable(PARAM_ID) int id, @RequestBody Person person) {
		logger.info(LOG_ENTER_METHOD + "updatePerson()" + LOG_CLOSE);
		person.setId(id);
		person.setVersion(personRepository.getOne(id).getVersion());
		person = personRepository.save(person);
		logger.info(LOG_TEXT + "Person with ID=" + id + " was updated" + LOG_CLOSE);
		HttpHeaders header = new HttpHeaders();
		logger.info(LOG_OUT_OF_METHOD + "updatePerson()" + LOG_CLOSE);
		return new ResponseEntity<Person>(person, header, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deletePerson(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "deletePerson()" + LOG_CLOSE);
		personRepository.delete(id);
		logger.info(LOG_TEXT + "Person with ID=" + id + " was deleted" + LOG_CLOSE);
		HttpHeaders header = new HttpHeaders();
		logger.info(LOG_OUT_OF_METHOD + "deletePerson()" + LOG_CLOSE);
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
