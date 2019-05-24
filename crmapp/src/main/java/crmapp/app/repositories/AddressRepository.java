package crmapp.app.repositories;

import crmapp.app.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<Address, Integer> {

    @Query("SELECT address FROM Address address WHERE contractor.id = :contractorId")
    List<Address> findAllByContractorId(@Param("contractorId") Integer contractorId);

}
