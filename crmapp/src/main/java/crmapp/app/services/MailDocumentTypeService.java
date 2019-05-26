package crmapp.app.services;

import crmapp.app.entities.MailDocumentType;
import crmapp.app.repositories.MailDocumentTypeRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class MailDocumentTypeService extends AbstractService<MailDocumentType, MailDocumentTypeRepository> {
}
