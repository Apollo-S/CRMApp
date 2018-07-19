package crmapp.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.ClientAccount;
import crmapp.app.repositories.ClientAccountRepository;

@Service
@Transactional
public class ClientAccountService extends AbstractService<ClientAccount, ClientAccountRepository> {

}
