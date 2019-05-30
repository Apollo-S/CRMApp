package crmapp.app.services;

import crmapp.app.entities.Contractor;
import crmapp.app.repositories.ContractorRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorService extends BaseServiceImpl<Contractor, ContractorRepository> {

    public List<Contractor> findAllByContractorType(String contractorType) {
        return repository.findAllByContractorType(contractorType);
    }
}
