package crmapp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.ClientAccount;
import crmapp.app.repositories.ClientAccountRepository;

@Service
@Transactional
public class ClientAccountService extends AbstractService<ClientAccount, ClientAccountRepository> {

	@Autowired
	private ClientService clientService;

	public List<ClientAccount> findAllByClientId(Integer clientId) {
		List<ClientAccount> accounts = repository.findAllByClientId(clientId);
		return accounts;
	}

}
