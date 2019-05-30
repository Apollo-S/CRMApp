package crmapp.app.services;

import crmapp.app.entities.Person;
import crmapp.app.repositories.PersonRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends BaseServiceImpl<Person, PersonRepository> {
}
