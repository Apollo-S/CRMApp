package crmapp.app.repositories;

import crmapp.app.entities.Contractor;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorRepository extends BaseRepository<Contractor, Integer> {

    @Query("SELECT c FROM Contractor c WHERE c.contractorType.id = (" +
            "SELECT ct.id FROM ContractorType ct WHERE ct.code = :contractorType)")
    List<Contractor> findAllByContractorType(@Param("contractorType") String contractorType);

}
