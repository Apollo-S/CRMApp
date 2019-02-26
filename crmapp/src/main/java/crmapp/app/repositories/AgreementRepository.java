package crmapp.app.repositories;

import java.util.List;

import crmapp.app.entities.Agreement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends BaseRepository<Agreement, Integer>{
	
	@Query("SELECT ca FROM Agreement ca WHERE ca.client.id = :clientId")
	List<Agreement> findAllAgreementsByClientId(@Param("clientId") Integer clientId);

}
