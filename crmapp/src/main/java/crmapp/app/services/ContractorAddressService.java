package crmapp.app.services;

import crmapp.app.entities.ContractorAddress;
import crmapp.app.repositories.ContractorAddressRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorAddressService extends BaseServiceImpl<ContractorAddress, ContractorAddressRepository> {

    public List<ContractorAddress> findAllByContractorId(Integer id) {
        return repository.findAllByContractorId(id);
    }

}
