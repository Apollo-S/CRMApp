package crmapp.app.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.Client;
import crmapp.app.repositories.ClientRepository;

@Service
@Transactional
public class ClientService {

	private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	@Autowired
	private ClientRepository repository;

	public List<Client> getAll() {
		logger.info("OK: ClientService.getAll()");
		List<Client> clients = repository.findAll();
		logger.info("OK: Clients with quantity="+ clients.size() + " found");
		return clients;
	}

	public Client getById(int id) {
		logger.info("OK: ClientService.getById()");
		Client client = repository.findOne(id);
		logger.info("OK: Client with ID="+ id + " found");
		return client;
	}

	public Client save(Client client) {
		logger.info("OK: ClientService.save()");
		client.setVersion(0);
		client = repository.save(client);
		logger.info("OK: Client saved: " + client);
		return client;
	}

	public Client update(int id, Client client) {
		logger.info("OK: ClientService.update()");
		client.setId(id);
		int actualVersionNumber = repository.getOne(id).getVersion();
		client.setVersion(actualVersionNumber);
		client = repository.save(client);
		logger.info("OK: Client updated: " + client);
		return client;
	}

	public void delete(int id) {
		logger.info("OK: ClientService.delete()");
		repository.delete(id);
		logger.info("OK: Client deleted successfully");
	}

}
