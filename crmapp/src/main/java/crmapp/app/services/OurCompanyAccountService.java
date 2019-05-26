package crmapp.app.services;

import crmapp.app.entities.OurCompanyAccount;
import crmapp.app.repositories.OurCompanyAccountRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class OurCompanyAccountService extends AbstractService<OurCompanyAccount, OurCompanyAccountRepository> {
}
