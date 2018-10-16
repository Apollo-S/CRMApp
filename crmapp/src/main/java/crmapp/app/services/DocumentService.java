package crmapp.app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.Direction.fromString;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.Document;
import crmapp.app.repositories.DocumentRepository;

@Service
public class DocumentService extends AbstractService<Document, DocumentRepository> {

    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private DocumentStatusService documentStatusService;

    @Transactional(readOnly = true)
    public List<Document> getAllByAgreementId(int id) {
        logger.info("OK: DocumentService.getAllByAgreementId()");
        List<Document> documents = super.repository.findAllDocumentsByAgreementId(id);
        logger.info("OK: Count of documents equals " + documents.size());
        return documents;
    }

    @Transactional(readOnly = true)
    public List<Document> getAllByFilterAndSort(List<Integer> docTypes, List<Integer> docStatuses,
                                                List<Integer> clients, String sortType, String sortField) {
        logger.info("OK: DocumentService.getAllByFilterAndSort()");
        List<Document> documents;
        if (docTypes.get(0) == 0 || docTypes.isEmpty()) {
            docTypes = documentTypeService.findAllEntityIds();
        }
        if (docStatuses.get(0) == 0 || docStatuses.isEmpty()) {
            docStatuses = documentStatusService.findAllEntityIds();
        }
        if (clients.get(0) == 0 || clients.isEmpty()) {
            clients = clientService.findAllEntityIds();
        }
        Sort sort = new Sort(fromString(sortType), sortField);
        documents = super.repository.findAllDocumentsByFilterAndSort(docTypes, docStatuses, clients, sort);
        logger.info("OK: Count of documents equals " + documents.size());
        return documents;
    }

}
