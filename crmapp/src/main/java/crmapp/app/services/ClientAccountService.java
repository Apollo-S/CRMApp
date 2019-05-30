package crmapp.app.services;

import crmapp.app.entities.Client;
import crmapp.app.entities.ClientAccount;
import crmapp.app.repositories.ClientAccountRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientAccountService extends ExtendedBaseServiceImpl<ClientAccount, ClientAccountRepository> {

	@Autowired
	private ClientService clientService;

	public ClientAccount save(int clientId, ClientAccount account) {
		Client client = clientService.findById(clientId);
		account.setClient(client);
		account = this.save(account);
		return account;
	}

	public ClientAccount updateWithClientId(int clientId, ClientAccount account) {
		Client client = clientService.findById(clientId);
		account.setClient(client);
		account = this.update(account);
		return account;
	}

}
