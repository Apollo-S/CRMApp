package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
	
	public List<Integer> findAllDocumentTypeIds();

}
