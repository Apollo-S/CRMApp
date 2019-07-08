package crmapp.app.repositories.experimental;

import crmapp.app.entities.experimental.Address;
import crmapp.app.entities.experimental.ContractorAddress;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorAddressRepository extends BaseRepository<ContractorAddress, Integer> {

    @Query("SELECT ca.address FROM ContractorAddress ca WHERE ca.contractor.id = :contractorId")
    List<Address> findAllByContractorId(@Param("contractorId") Integer contractorId);

}
