package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.Document;

public interface DocumentRepository extends BaseRepository<Document, Integer> {

	public static final String AGREEMENT_ID = "agreementId";
	public static final String DOC_STATUSES = "docStatuses";
	public static final String DOC_TYPES = "docTypes";
	public static final String CLIENTS = "clients";

	@Query("SELECT doc FROM Document doc WHERE doc.agreement.id = :agreementId")
	public List<Document> findAllDocumentsByAgreementId(@Param(AGREEMENT_ID) Integer agreementId);

	@Query("select doc from Document doc where doc.docType.id in (:docTypes) " +
			"and doc.status.id in (:docStatuses) and doc.agreement.client.id in (:clients)")
	public List<Document> findAllDocumentsByFilterAndSort(@Param(DOC_TYPES) List<Integer> docTypes, 
			@Param(DOC_STATUSES) List<Integer> docStatuses, @Param(CLIENTS) List<Integer> clients, Sort sort);

}
