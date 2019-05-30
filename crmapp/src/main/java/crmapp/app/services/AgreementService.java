package crmapp.app.services;

import java.util.List;

import crmapp.app.entities.Agreement;
import crmapp.app.entities.AgreementType;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crmapp.app.repositories.AgreementRepository;

@Service
@Transactional
public class AgreementService extends BaseServiceImpl<Agreement, AgreementRepository> {

	public List<Agreement> findAllByClientId(int clientId) {
		return repository.findAllAgreementsByClientId(clientId);
	}

	public List<Agreement> findAllAgreementsByType(AgreementType agrType) {
		return repository.findAllAgreementsByType(agrType);
	}

}
