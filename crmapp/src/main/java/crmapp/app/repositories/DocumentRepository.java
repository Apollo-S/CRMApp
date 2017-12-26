package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

	public List<Document> findAllDocumentsByAgreementId(@Param("agreementId") Integer agreementId);
	
}
