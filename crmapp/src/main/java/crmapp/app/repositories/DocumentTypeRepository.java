package crmapp.app.repositories;

import crmapp.app.entities.DocumentType;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends BaseRepository<DocumentType, Integer> {

}
