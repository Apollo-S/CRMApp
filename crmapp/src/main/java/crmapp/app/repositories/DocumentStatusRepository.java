package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.DocumentStatus;

public interface DocumentStatusRepository extends JpaRepository<DocumentStatus, Integer> {

	public List<Integer> findAllDocumentStatusIds();

}
