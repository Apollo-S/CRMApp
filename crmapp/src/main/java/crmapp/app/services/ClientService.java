package crmapp.app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.Client;
import crmapp.app.repositories.ClientRepository;

@Service
@Transactional
public class ClientService extends AbstractService<Client, ClientRepository> {
}
