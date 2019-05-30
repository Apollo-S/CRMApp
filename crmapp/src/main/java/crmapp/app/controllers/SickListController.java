package crmapp.app.controllers;

import java.util.List;

import crmapp.app.controllers.base.BaseController;
import crmapp.app.services.SickListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.SickList;
import crmapp.app.repositories.SickListRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/employees")
public class SickListController extends BaseController<SickList, SickListService> {

	@Autowired
	private SickListRepository sickListRepository;

	@GetMapping(value = "/{employeeId}/sicklists", headers = HEADER_JSON)
	public ResponseEntity<List<SickList>> getAllSickListsByEmployeeId(@PathVariable("employeeId") int employeeId) {
		List<SickList> sickLists = sickListRepository.findAllSickListsByEmployeeId(employeeId);
		if (sickLists == null) {
			return new ResponseEntity<List<SickList>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SickList>>(sickLists, HttpStatus.OK);
	}

	@GetMapping(value = "/sicklists", headers = HEADER_JSON)
	public ResponseEntity<List<SickList>> getAllSickLists() {
		List<SickList> sickLists = sickListRepository.findAll();
		if (sickLists.size() == 0) {
			return new ResponseEntity<List<SickList>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SickList>>(sickLists, HttpStatus.OK);
	}

	@GetMapping(value = "/sicklists/{id}", headers = HEADER_JSON)
	public ResponseEntity<SickList> getSickListById(@PathVariable(PARAM_ID) int id) {
		SickList sickList = sickListRepository.findOne(id);
		if (sickList == null) {
			return new ResponseEntity<SickList>(sickList, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SickList>(sickList, HttpStatus.OK);
	}

}
