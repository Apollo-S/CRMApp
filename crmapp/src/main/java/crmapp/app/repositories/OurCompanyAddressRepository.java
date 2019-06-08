package crmapp.app.repositories;

import crmapp.app.entities.OurCompanyAddress;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OurCompanyAddressRepository extends ExtendedBaseRepository<OurCompanyAddress, Integer> {
}
