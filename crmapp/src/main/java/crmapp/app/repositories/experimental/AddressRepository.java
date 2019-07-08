package crmapp.app.repositories.experimental;

import crmapp.app.entities.experimental.Address;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<Address, Integer> {

}
