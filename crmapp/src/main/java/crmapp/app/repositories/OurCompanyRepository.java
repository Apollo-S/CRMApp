package crmapp.app.repositories;

import crmapp.app.entities.OurCompany;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OurCompanyRepository extends BaseRepository<OurCompany, Integer> {

}
