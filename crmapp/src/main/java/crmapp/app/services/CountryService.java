package crmapp.app.services;

import crmapp.app.entities.Country;
import crmapp.app.repositories.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryService extends AbstractService<Country, CountryRepository> {
}
