package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	public List<Integer> findAllClientIds();

}
