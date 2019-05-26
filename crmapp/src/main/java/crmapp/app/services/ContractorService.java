package crmapp.app.services;

import crmapp.app.entities.Contractor;
import crmapp.app.repositories.ContractorRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorService extends AbstractService<Contractor, ContractorRepository> {

    public List<Contractor> findAllByContractorType(String contractorType) {
        return repository.findAllByContractorType(contractorType);
    }
}
