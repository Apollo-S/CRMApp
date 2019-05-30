package crmapp.app.services;

import crmapp.app.entities.AgreementType;
import crmapp.app.repositories.AgreementTypeRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgreementTypeService extends BaseServiceImpl<AgreementType, AgreementTypeRepository> {

}
