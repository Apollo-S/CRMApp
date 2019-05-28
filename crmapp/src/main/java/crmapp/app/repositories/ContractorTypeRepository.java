package crmapp.app.repositories;

import crmapp.app.entities.ContractorType;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorTypeRepository extends BaseRepository<ContractorType, Integer> {
}
