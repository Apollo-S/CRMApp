package crmapp.app.repositories;

import crmapp.app.entities.CurrencyType;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyTypeRepository extends BaseRepository<CurrencyType, Integer> {
}
