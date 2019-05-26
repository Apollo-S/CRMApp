package crmapp.app.services;

import crmapp.app.entities.AgreementType;
import crmapp.app.repositories.AgreementTypeRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgreementTypeService extends AbstractService<AgreementType, AgreementTypeRepository> {

}
