package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.Agreement;

public interface AgreementRepository extends JpaRepository<Agreement, Integer>{

}
