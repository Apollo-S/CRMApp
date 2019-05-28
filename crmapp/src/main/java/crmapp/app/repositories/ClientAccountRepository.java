package crmapp.app.repositories;

import crmapp.app.entities.ClientAccount;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientAccountRepository extends BaseRepository<ClientAccount, Integer> {

	@Query("SELECT ca FROM ClientAccount ca WHERE ca.client.id = :clientId")
	List<ClientAccount> findAllByClientId(@Param("clientId") Integer clientId);
	
}


