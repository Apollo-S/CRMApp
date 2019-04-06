package crmapp.app.repositories;

import crmapp.app.entities.ClientAgreement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientAgreementRepository extends BaseRepository<ClientAgreement, Integer> {

    @Query("SELECT ca FROM ClientAgreement ca WHERE ca.client.id = :clientId")
    List<ClientAgreement> findAllAgreementsByClientId(@Param("clientId") Integer clientId);

}
