package crmapp.app.repositories.experimental;

import crmapp.app.entities.experimental.ContractorType;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorTypeRepository extends BaseRepository<ContractorType, Integer> {
}
