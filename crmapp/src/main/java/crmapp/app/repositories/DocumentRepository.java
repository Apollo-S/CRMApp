package crmapp.app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.Document;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends BaseRepository<Document, Integer> {

    @Query("SELECT doc FROM Document doc WHERE doc.agreement.id = :agreementId")
    List<Document> findAllDocumentsByAgreementId(@Param("agreementId") Integer agreementId);

    @Query("SELECT doc FROM Document doc WHERE doc.docType.id IN (:docTypes) " +
            "AND doc.status.id IN (:docStatuses) AND doc.agreement.client.id IN (:clients) " +
            "AND (doc.dated >= COALESCE((:datedStart), '1900-01-01') " +
            "AND doc.dated <= COALESCE((:datedFinal), '3001-01-01'))")
    List<Document> findAllDocumentsByFilterAndSort(@Param("docTypes") List<Integer> docTypes,
                                                   @Param("docStatuses") List<Integer> docStatuses,
                                                   @Param("clients") List<Integer> clients,
                                                   @Param("datedStart") Date datedStart,
                                                   @Param("datedFinal") Date datedFinal, Sort sort);

}
