package crmapp.app.services;

import crmapp.app.entities.Person;
import crmapp.app.repositories.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends AbstractService<Person, PersonRepository> {
}
