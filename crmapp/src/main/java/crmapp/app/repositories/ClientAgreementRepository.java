package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.ClientAgreement;

public interface ClientAgreementRepository extends BaseRepository<ClientAgreement, Integer>{
	
	@Query("SELECT ca FROM ClientAgreement ca WHERE ca.client.id = :clientId")
	public List<ClientAgreement> findAllAgreementsByClientId(@Param("clientId") Integer clientId);

}
