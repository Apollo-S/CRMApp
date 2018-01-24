package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import crmapp.app.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
	
	@Query(value = "select c.alias as `label` from client c", nativeQuery = true)
	public List<String> findAllClientsAliases();

}
