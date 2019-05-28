package crmapp.app.repositories;

import crmapp.app.entities.Person;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends BaseRepository<Person, Integer> {

}
