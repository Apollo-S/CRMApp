package crmapp.app.repositories;

import java.util.List;

import crmapp.app.entities.Agreement;
import crmapp.app.entities.AgreementType;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends BaseRepository<Agreement, Integer> {
	
	@Query("SELECT a FROM Agreement a WHERE a.client.id = :clientId")
	List<Agreement> findAllAgreementsByClientId(@Param("clientId") Integer clientId);

    @Query("SELECT a FROM Agreement a WHERE a.agreementType = :agrType")
    List<Agreement> findAllAgreementsByType(@Param("agrType") AgreementType agrType);

}
