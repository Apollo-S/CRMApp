package crmapp.app.services;

import java.util.List;

import crmapp.app.entities.Agreement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.repositories.AgreementRepository;

@Service
@Transactional
public class AgreementService extends AbstractService<Agreement, AgreementRepository> {

	public List<Agreement> findAllByClientId(int clientId) {
		return repository.findAllAgreementsByClientId(clientId);
	}

}
