package crmapp.app.repositories;

import crmapp.app.entities.ContractorAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorAddressRepository extends BaseRepository<ContractorAddress, Integer> {

    @Query("SELECT address FROM ContractorAddress address WHERE address.contractor.id = :contractorId")
    List<ContractorAddress> findAllByContractorId(@Param("contractorId") Integer contractorId);

}
