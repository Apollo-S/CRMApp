package crmapp.app.services;

import crmapp.app.entities.OurCompanyAccount;
import crmapp.app.repositories.OurCompanyAccountRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OurCompanyAccountService extends ExtendedBaseServiceImpl<OurCompanyAccount, OurCompanyAccountRepository> {
}
