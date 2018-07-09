package crmapp.app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.springframework.data.domain.Sort.Direction.fromString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.Document;
import crmapp.app.repositories.ClientRepository;
import crmapp.app.repositories.DocumentRepository;
import crmapp.app.repositories.DocumentStatusRepository;
import crmapp.app.repositories.DocumentTypeRepository;

@Service
@Transactional
public class DocumentService implements BaseService<Document> {

	private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

	@Autowired
	private DocumentRepository docRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private DocumentTypeRepository docTypeRepository;

	@Autowired
	private DocumentStatusRepository docStatusRepository;

	@Override
	public List<Document> getAll() {
		logger.info("OK: DocumentService.getAll()");
		List<Document> documents = ((JpaRepository<Document,Integer>) docRepository).findAll();
		logger.info("OK: Documents in quantity=" + documents.size() + " found");
		return documents;
	}

	@Override
	public Document getById(int id) {
		logger.info("OK: DocumentService.getById()");
		Document document = docRepository.findOne(id);
		logger.info("OK: Document with ID="+ id + " found");
		return document;
	}

	public List<Document> getAllByAgreementId(int id) {
		logger.info("OK: DocumentService.getAllByAgreementId()");
		List<Document> documents = docRepository.findAllDocumentsByAgreementId(id);
		logger.info("OK: Count of documents equals " + documents.size());
		return documents;
	}

	@Override
	public Document save(Document document) {
		logger.info("OK: DocumentService.save()");
		document.setVersion(0);
		document = docRepository.save(document);
		logger.info("OK: Document saved: " + document);
		return document;
	}

	public List<Document> getAllByFilterAndSort(List<Integer> docTypes, List<Integer> docStatuses, List<Integer> clients,
			String sortType, String sortField) {
		logger.info("OK: DocumentService.getAllByFilterAndSort()");
		List<Document> documents;
		if (docTypes.get(0) == 0 || docTypes.isEmpty()) {
			docTypes = docTypeRepository.findAllEntityIds();
		}
		if (docStatuses.get(0) == 0 || docStatuses.isEmpty()) {
			docStatuses = docStatusRepository.findAllEntityIds();
		}
		if (clients.get(0) == 0 || clients.isEmpty()) {
			clients = clientRepository.findAllEntityIds();
		}
		Sort sort = new Sort(fromString(sortType), sortField);
		documents = docRepository.findAllDocumentsByFilterAndSort(docTypes, docStatuses, clients, sort);
		logger.info("OK: Count of documents equals " + documents.size());
		return documents;
	}

	@Override
	public Document update(int id, Document document) {
		logger.info("OK: DocumentService.update()");
		document.setId(id);
		int actualVersionNumber = docRepository.getOne(id).getVersion();
		document.setVersion(actualVersionNumber);
		document = docRepository.save(document);
		logger.info("OK: Document updated: " + document);
		return document;
	}

	@Override
	public void delete(int id) {
		logger.info("OK: DocumentService.delete()");
		docRepository.delete(id);
		logger.info("OK: Document deleted successfully");
	}

}
