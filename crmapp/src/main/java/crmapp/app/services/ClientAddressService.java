package crmapp.app.services;

import crmapp.app.entities.Client;
import crmapp.app.entities.ClientAddress;
import crmapp.app.repositories.ClientAddressRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientAddressService extends ExtendedBaseServiceImpl<ClientAddress, ClientAddressRepository> {

	@Autowired
	private ClientService clientService;

	public ClientAddress save(int clientId, ClientAddress address) {
		Client client = clientService.findById(clientId);
		address.setClient(client);
		return this.save(address);
	}

	public ClientAddress updateWithClientId(int clientId, ClientAddress address) {
		Client client = clientService.findById(clientId);
		address.setClient(client);
		return this.update(address);
	}

}
