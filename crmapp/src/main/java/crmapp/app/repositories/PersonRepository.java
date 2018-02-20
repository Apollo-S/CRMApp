package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import crmapp.app.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
