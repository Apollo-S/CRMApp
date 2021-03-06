package crmapp.app.services;

import java.util.*;

import crmapp.app.entities.DocumentFilter;
import crmapp.app.services.base.BaseServiceImpl;
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
public class DocumentService extends BaseServiceImpl<Document, DocumentRepository> {

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
                                                List<Integer> clients, Date datedStart, Date datedFinal, String sortType, String sortField) {
        logger.info("OK: DocumentService.getAllByFilterAndSort()");
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
        List<Document> documents = super.repository.findAllDocumentsByFilterAndSort(docTypes, docStatuses, clients, datedStart, datedFinal, sort);
        logger.info("OK: Count of documents equals " + documents.size());
        return documents;
    }

    @Transactional(readOnly = true)
    public List<Document> getAllByFilterAndSort(DocumentFilter docFilter) {
        return getAllByFilterAndSort(docFilter.getDocTypes(), docFilter.getDocStatuses(), docFilter.getClients(),
                docFilter.getDatedStart(), docFilter.getDatedFinal(), docFilter.getSortType(), docFilter.getSortField());
    }

}
