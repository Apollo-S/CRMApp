package crmapp.app.services;

import crmapp.app.entities.ClientAgreement;
import crmapp.app.repositories.ClientAgreementRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientAgreementService extends BaseServiceImpl<ClientAgreement, ClientAgreementRepository> {

    public List<ClientAgreement> findAllByClientId(int clientId) {
        return repository.findAllAgreementsByClientId(clientId);
    }

}
