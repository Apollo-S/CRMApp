package crmapp.app.repositories;

import crmapp.app.entities.ClientAddress;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAddressRepository extends BaseRepository<ClientAddress, Integer> {
}
