package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Integer>{

}
