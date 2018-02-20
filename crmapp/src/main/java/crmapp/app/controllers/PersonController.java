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
		List<Person> persons = personRepository.findAll();
		if (persons.size() == 0) {
			return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Person> getPersonById(@PathVariable(PARAM_ID) int id) {
		Person person = personRepository.findOne(id);
		if (person == null) {
			logger.info("<==/////////// Person not found with ID=" + id + "///////////==>");
			return new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);
		}
		logger.info("<==/////////// Person with ID=" + person.getId() + " was found ///////////==>");
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@PostMapping(value = "", headers = HEADER_JSON)
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		person.setVersion(0);
		person = personRepository.save(person);
		logger.info("<==/////////// Added person, ID=" + person.getId() + "///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Person>(person, header, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Person> updatePerson(@PathVariable(PARAM_ID) int id, @RequestBody Person person) {
		person.setId(id);
		person.setVersion(personRepository.getOne(id).getVersion());
		person = personRepository.save(person);
		logger.info("<==/////////// Person with ID=" + person.getId() + " was updated ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Person>(person, header, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deletePerson(@PathVariable(PARAM_ID) int id) {
		personRepository.delete(id);
		logger.info("<==/////////// Person with ID=" + id + " was deleted ///////////==>");
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
