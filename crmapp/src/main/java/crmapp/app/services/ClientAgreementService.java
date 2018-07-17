package crmapp.app.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.entities.ClientAgreement;
import crmapp.app.repositories.ClientAgreementRepository;

@Service
@Transactional
public class ClientAgreementService extends AbstractService<ClientAgreement, ClientAgreementRepository> {

	public List<ClientAgreement> findAllByClientId(int clientId) {
		List<ClientAgreement> agreements = repository.findAllAgreementsByClientId(clientId);
		return agreements;
	}

}
