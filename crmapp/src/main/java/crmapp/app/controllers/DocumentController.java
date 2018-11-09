package crmapp.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crmapp.app.entities.Document;
import crmapp.app.services.DocumentService;

@RestController
@RequestMapping(value = "/api")
public class DocumentController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;

	@GetMapping(value = "/documents", headers = HEADER_JSON)
	public ResponseEntity<List<Document>> getAllDocuments() {
		logger.info(LOG_ENTER_METHOD + "getAllDocuments()" + LOG_CLOSE);
		List<Document> documents = documentService.getAll();
		if (documents.size() == 0) {
			logger.info(LOG_ERROR + "Documents were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of documents: " + documents.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllDocuments()" + LOG_CLOSE);
		return new ResponseEntity<>(documents, HttpStatus.OK);
	}

	@PostMapping(value = "/documents/filter/docTypes=[{docTypes}]&docStatuses=[{docStatuses}]&clients=[{clients}]&sortField={sortField}&sortType={sortType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Document>> getAllDocumentsByFilter(@PathVariable("docTypes") List<Integer> docTypes, 
			@PathVariable("docStatuses") List<Integer> docStatuses, @PathVariable("clients") List<Integer> clients,
			@PathVariable("sortField") String sortField, @PathVariable("sortType") String sortType) {
		logger.info(LOG_ENTER_METHOD + "getAllDocumentsByFilter()" + LOG_CLOSE);
		List<Document> documents = documentService.getAllByFilterAndSort(docTypes, docStatuses, clients, sortType, sortField);
		if (documents.size() == 0) {
			logger.info(LOG_ERROR + "Documents were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of documents equals " + documents.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllDocumentsByFilter()" + LOG_CLOSE);
		return new ResponseEntity<>(documents, HttpStatus.OK);
	}

	@GetMapping(value = "/agreements/{agreementId}/documents", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Document>> getAllDocumentsByAgreementId(@PathVariable("agreementId") int agreementId) {
		logger.info(LOG_ENTER_METHOD + "getAllDocumentsByAgreementId()" + LOG_CLOSE);
		List<Document> documents = documentService.getAllByAgreementId(agreementId);
		if (documents.size() == 0) {
			logger.info(LOG_ERROR + "Documents were not found" + LOG_CLOSE);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info(LOG_TEXT + "Count of documents equals " + documents.size() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getAllDocumentsByAgreementId()" + LOG_CLOSE);
		return new ResponseEntity<>(documents, HttpStatus.OK);
	}

	@GetMapping(value = "/documents/{id}", headers = HEADER_JSON)
	public ResponseEntity<Document> getDocumentById(@PathVariable(PARAM_ID) int id) {
		logger.info(LOG_ENTER_METHOD + "getDocumentById()" + LOG_CLOSE);
		Document document = documentService.getById(id);
		if (document == null) {
			logger.info(LOG_ERROR + "Document with ID=" + id + "wasn't found" + LOG_CLOSE);
			return new ResponseEntity<>(document, HttpStatus.NOT_FOUND);
		}
		logger.info(LOG_TEXT + "Document with ID=" + id + " was found: " + document + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "getDocumentById()" + LOG_CLOSE);
		return new ResponseEntity<>(document, HttpStatus.OK);
	}

	@PostMapping(value = "/documents", headers = HEADER_JSON)
	public ResponseEntity<Document> addDocument(@RequestBody Document document) {
		logger.info(LOG_ENTER_METHOD + "addDocument()" + LOG_CLOSE);
		document = documentService.save(document);
		logger.info(LOG_TEXT + "Document added with ID=" + document.getId() + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "addDocument()" + LOG_CLOSE);
		return new ResponseEntity<Document>(document, new HttpHeaders(), HttpStatus.CREATED);
	}

	@PutMapping(value = "/documents/{id}", headers = HEADER_JSON)
	public ResponseEntity<Document> updateDocument(@PathVariable(PARAM_ID) int id, @RequestBody Document document) {
		logger.info(LOG_ENTER_METHOD + "updateDocument()" + LOG_CLOSE);
		document = documentService.update(id, document);
		logger.info(LOG_TEXT + "Document with ID=" + id + " was updated: " + document + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "updateDocument()" + LOG_CLOSE);
		return new ResponseEntity<Document>(document, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/documents/{id}", headers = HEADER_JSON)
	public ResponseEntity<Void> deleteDocument(@PathVariable(PARAM_ID) int id, @RequestBody Document document) {
		logger.info(LOG_ENTER_METHOD + "deleteDocument()" + LOG_CLOSE);
		documentService.delete(id);
		logger.info(LOG_TEXT + "Document with ID=" + id + " was deleted: " + document + LOG_CLOSE);
		logger.info(LOG_OUT_OF_METHOD + "deleteDocument()" + LOG_CLOSE);
		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NO_CONTENT);
	}

}
