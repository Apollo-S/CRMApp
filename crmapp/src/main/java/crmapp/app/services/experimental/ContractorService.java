package crmapp.app.services.experimental;

import crmapp.app.entities.experimental.Contractor;
import crmapp.app.repositories.experimental.ContractorRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorService extends BaseServiceImpl<Contractor, ContractorRepository> {

    public List<Contractor> findAllByContractorType(String contractorType) {
        return repository.findAllByContractorType(contractorType);
    }
}
