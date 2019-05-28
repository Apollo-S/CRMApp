package crmapp.app.repositories;

import crmapp.app.entities.Client;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends BaseRepository<Client, Integer> {

}
