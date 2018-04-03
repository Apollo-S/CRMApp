package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import crmapp.app.entities.Document;

public interface DocumentRepository extends BaseRepository<Document, Integer> {

	public static final String AGREEMENT_ID = "agreementId";
	public static final String DOC_STATUSES = "docStatuses";
	public static final String DOC_TYPES = "docTypes";
	public static final String CLIENTS = "clients";

	public List<Document> findAllDocumentsByAgreementId(@Param(AGREEMENT_ID) Integer agreementId);

	public List<Document> findAllDocumentsByFilter(@Param(DOC_TYPES) List<Integer> docTypes, 
			@Param(DOC_STATUSES) List<Integer> docStatuses, @Param(CLIENTS) List<Integer> clients);

}
