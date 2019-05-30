package crmapp.app.services;

import crmapp.app.entities.MailOutput;
import crmapp.app.repositories.MailOutputRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MailOutputService extends BaseServiceImpl<MailOutput, MailOutputRepository> {
}
