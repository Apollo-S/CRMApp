package crmapp.app.services;

import crmapp.app.entities.Country;
import crmapp.app.repositories.CountryRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryService extends AbstractService<Country, CountryRepository> {
}
