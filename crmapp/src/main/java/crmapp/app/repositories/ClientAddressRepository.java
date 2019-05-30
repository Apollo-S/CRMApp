package crmapp.app.repositories;

import crmapp.app.entities.ClientAddress;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAddressRepository extends ExtendedBaseRepository<ClientAddress, Integer> {

}
