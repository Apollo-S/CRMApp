package crmapp.app.services;

import crmapp.app.entities.ClientAgreement;
import crmapp.app.repositories.ClientAgreementRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientAgreementService extends AbstractService<ClientAgreement, ClientAgreementRepository> {

    public List<ClientAgreement> findAllByClientId(int clientId) {
        return repository.findAllAgreementsByClientId(clientId);
    }

}
