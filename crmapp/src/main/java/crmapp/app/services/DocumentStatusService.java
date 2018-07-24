package crmapp.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.DocumentStatus;
import crmapp.app.repositories.DocumentStatusRepository;

@Service
@Transactional
public class DocumentStatusService extends AbstractService<DocumentStatus, DocumentStatusRepository> {
	
}