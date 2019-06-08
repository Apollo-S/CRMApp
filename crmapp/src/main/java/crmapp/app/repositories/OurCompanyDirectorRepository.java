package crmapp.app.repositories;

import crmapp.app.entities.OurCompanyDirector;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OurCompanyDirectorRepository extends ExtendedBaseRepository<OurCompanyDirector, Integer> {
}