package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.ClientDirector;

public interface ClientDirectorRepository extends JpaRepository<ClientDirector, Integer>{

	@Query("SELECT cd FROM ClientDirector cd WHERE cd.client.id = :clientId")
	public List<ClientDirector> findAllDirectorsByClientId(@Param("clientId") Integer clientId);
	
}


