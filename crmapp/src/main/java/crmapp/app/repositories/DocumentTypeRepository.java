package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {

}
