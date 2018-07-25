package crmapp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.Client;
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

	public ClientAccount save(int clientId, ClientAccount account) {
		Client client = clientService.getById(clientId);
		account.setClient(client);
		account = this.save(account);
		return account;
	}

	public ClientAccount updateWithClientId(int clientId, ClientAccount account) {
		Client client = clientService.getById(clientId);
		account.setClient(client);
		account = this.update(account);
		return account;
	}

}
