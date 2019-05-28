package crmapp.app.repositories;

import crmapp.app.entities.Country;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends BaseRepository<Country, Integer> {
}


