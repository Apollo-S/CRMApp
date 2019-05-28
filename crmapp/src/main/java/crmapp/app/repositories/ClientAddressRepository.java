package crmapp.app.repositories;

import crmapp.app.entities.ClientAddress;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientAddressRepository extends BaseRepository<ClientAddress, Integer> {

    @Query("SELECT ca FROM ClientAddress ca WHERE ca.client.id = :clientId")
    List<ClientAddress> findAllByClientId(@Param("clientId") Integer clientId);

}
