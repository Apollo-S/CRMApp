package crmapp.app.repositories;

import crmapp.app.entities.ClientAccount;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepository extends ExtendedBaseRepository<ClientAccount, Integer> {

}


