package crmapp.app.services;

import crmapp.app.entities.ContractorType;
import crmapp.app.repositories.ContractorTypeRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class ContractorTypeService extends AbstractService<ContractorType, ContractorTypeRepository> {
}
