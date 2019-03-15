package crmapp.app.services;

import crmapp.app.entities.DocumentType;
import crmapp.app.repositories.DocumentTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AgreementTypeService extends AbstractService<DocumentType, DocumentTypeRepository> {

}
