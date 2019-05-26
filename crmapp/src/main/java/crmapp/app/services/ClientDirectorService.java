package crmapp.app.services;

import crmapp.app.entities.ClientDirector;
import crmapp.app.repositories.ClientDirectorRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientDirectorService extends AbstractService<ClientDirector, ClientDirectorRepository> {

    @Autowired
    private ClientService clientService;

    public List<ClientDirector> findAllByClientId(Integer clientId) {
        return repository.findAllDirectorsByClientId(clientId);
    }

    public ClientDirector save(int clientId, ClientDirector director) {
        director.setClient(clientService.findById(clientId));
        return this.save(director);
    }

    public ClientDirector updateWithClientId(int clientId, ClientDirector director) {
        director.setClient(clientService.findById(clientId));
        return this.update(director);
    }

}
