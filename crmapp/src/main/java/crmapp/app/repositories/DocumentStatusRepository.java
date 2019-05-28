package crmapp.app.repositories;

import crmapp.app.entities.DocumentStatus;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentStatusRepository extends BaseRepository<DocumentStatus, Integer> {

}
