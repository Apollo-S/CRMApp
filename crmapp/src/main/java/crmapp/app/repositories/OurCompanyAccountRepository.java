package crmapp.app.repositories;

import crmapp.app.entities.OurCompanyAccount;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OurCompanyAccountRepository extends ExtendedBaseRepository<OurCompanyAccount, Integer> {
}
