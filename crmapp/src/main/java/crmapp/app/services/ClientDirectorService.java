package crmapp.app.services;

import crmapp.app.entities.ClientDirector;
import crmapp.app.repositories.ClientDirectorRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ClientDirectorService extends ExtendedBaseServiceImpl<ClientDirector, ClientDirectorRepository> {
}
