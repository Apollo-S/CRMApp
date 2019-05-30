package crmapp.app.services;

import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import crmapp.app.entities.Client;
import crmapp.app.repositories.ClientRepository;

@Service
public class ClientService extends BaseServiceImpl<Client, ClientRepository> {
}
