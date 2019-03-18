package crmapp.app.repositories;

import java.util.List;

import crmapp.app.entities.Agreement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends BaseRepository<Agreement, Integer>{
	
	@Query("SELECT a FROM Agreement a WHERE a.client.id = :clientId")
	List<Agreement> findAllAgreementsByClientId(@Param("clientId") Integer clientId);

	@Query("SELECT a FROM Agreement a WHERE a.agreementType.code = :code")
	List<Agreement> findAllAgreementsByType(@Param("code") String code);

}
