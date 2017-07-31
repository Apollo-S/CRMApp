package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.DocumentStatus;

public interface DocumentStatusRepository extends JpaRepository<DocumentStatus, Integer> {

}
