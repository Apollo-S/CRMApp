package crmapp.app.services;

import crmapp.app.entities.MailInput;
import crmapp.app.repositories.MailInputRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class MailInputService extends AbstractService<MailInput, MailInputRepository> {
}
