package crmapp.app.services;

import crmapp.app.entities.ClientAccount;
import crmapp.app.repositories.ClientAccountRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientAccountService extends ExtendedBaseServiceImpl<ClientAccount, ClientAccountRepository> {
}
