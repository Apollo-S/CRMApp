package crmapp.app.repositories;

import crmapp.app.entities.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends BaseRepository<Country, Integer> {
}


