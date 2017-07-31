package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

}
