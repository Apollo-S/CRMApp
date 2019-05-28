package crmapp.app.repositories;

import crmapp.app.entities.Address;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<Address, Integer> {

}
