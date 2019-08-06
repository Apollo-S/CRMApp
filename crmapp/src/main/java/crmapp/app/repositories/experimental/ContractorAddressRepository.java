package crmapp.app.repositories.experimental;

import crmapp.app.entities.experimental.ContractorAddress;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorAddressRepository extends BaseRepository<ContractorAddress, Integer> {

//    @Query("SELECT ca.address FROM ContractorAddress ca WHERE ca.contractor.id = :contractorId")
//    List<Address> findAllByContractorId(@Param("contractorId") Integer contractorId);

}
