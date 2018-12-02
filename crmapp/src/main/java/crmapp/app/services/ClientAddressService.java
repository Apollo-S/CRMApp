package crmapp.app.services;

import crmapp.app.entities.Client;
import crmapp.app.entities.ClientAddress;
import crmapp.app.repositories.ClientAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientAddressService extends AbstractService<ClientAddress, ClientAddressRepository> {

	@Autowired
	private ClientService clientService;

	public List<ClientAddress> findAllByClientId(Integer clientId) {
		return repository.findAllClientAddressesByClientId(clientId);
	}

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
