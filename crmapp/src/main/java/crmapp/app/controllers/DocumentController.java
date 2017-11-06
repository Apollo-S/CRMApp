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

import crmapp.app.entities.Document;
import crmapp.app.repositories.DocumentRepository;

@RestController
@Transactional
@RequestMapping(value = "/api/documents")
public class DocumentController extends BaseController {

	@Autowired
	private DocumentRepository documentRepository;

	@GetMapping(value = REQUEST_MAPPING_EMPTY, headers = HEADER_JSON)
	public ResponseEntity<List<Document>> getAllDocuments() {
		List<Document> documents = documentRepository.findAll();
		if (documents.size() == 0) {
			return new ResponseEntity<List<Document>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Document>>(documents, HttpStatus.OK);
	}

	@GetMapping(value = REQUEST_MAPPING_BY_ID, headers = HEADER_JSON)
	public ResponseEntity<Document> getContractorById(@PathVariable(PARAM_ID) int id) {
		Document document = documentRepository.findOne(id);
		if (document == null) {
			return new ResponseEntity<Document>(document, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Document>(document, HttpStatus.OK);
	}

	@PostMapping(value = REQUEST_MAPPING_EMPTY, headers = HEADER_JSON)
	public ResponseEntity<Void> addDocument(@RequestBody Document document) {
		document = documentRepository.save(document);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@PutMapping(value = REQUEST_MAPPING_BY_ID, headers = HEADER_JSON)
	public ResponseEntity<Void> updateDocument(@PathVariable(PARAM_ID) int id, @RequestBody Document document) {
		document.setId(id);
		document.setVersion(documentRepository.getOne(id).getVersion());
		document = documentRepository.save(document);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.OK);
	}

	@DeleteMapping(value = REQUEST_MAPPING_BY_ID, headers = HEADER_JSON)
	public ResponseEntity<Void> deleteDocument(@PathVariable(PARAM_ID) int id, @RequestBody Document document) {
		documentRepository.delete(document);
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<Void>(header, HttpStatus.NO_CONTENT);
	}

}
