package crmapp.app.services;

import crmapp.app.entities.MailInput;
import crmapp.app.repositories.MailInputRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MailInputService extends BaseServiceImpl<MailInput, MailInputRepository> {
}
