package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.ClientAddress;

public interface ClientAddressRepository extends JpaRepository<ClientAddress, Integer> {

	@Query("SELECT ca FROM ClientAddress ca WHERE ca.client.id = :clientId")
	List<ClientAddress> findAllClientAddressesByClientId(@Param("clientId") Integer clientId);

}
