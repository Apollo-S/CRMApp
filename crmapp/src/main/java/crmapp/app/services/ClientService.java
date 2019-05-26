package crmapp.app.services;

import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;

import crmapp.app.entities.Client;
import crmapp.app.repositories.ClientRepository;

@Service
public class ClientService extends AbstractService<Client, ClientRepository> {
}
