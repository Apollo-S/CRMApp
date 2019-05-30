package crmapp.app.services;

import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.DocumentType;
import crmapp.app.repositories.DocumentTypeRepository;

@Service
@Transactional
public class DocumentTypeService extends BaseServiceImpl<DocumentType, DocumentTypeRepository> {

}
