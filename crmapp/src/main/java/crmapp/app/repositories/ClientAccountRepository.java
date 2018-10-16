package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.ClientAccount;

public interface ClientAccountRepository extends BaseRepository<ClientAccount, Integer>{

	@Query("SELECT ca FROM ClientAccount ca WHERE ca.client.id = :clientId")
	List<ClientAccount> findAllByClientId(@Param("clientId") Integer clientId);
	
}


