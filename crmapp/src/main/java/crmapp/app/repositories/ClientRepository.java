package crmapp.app.repositories;

import crmapp.app.entities.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends BaseRepository<Client, Integer> {

}
