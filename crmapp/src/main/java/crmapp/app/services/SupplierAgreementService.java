package crmapp.app.services;

import crmapp.app.entities.SupplierAgreement;
import crmapp.app.repositories.SupplierAgreementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierAgreementService extends AbstractService<SupplierAgreement, SupplierAgreementRepository> {

    public List<SupplierAgreement> findAllBySupplierId(int clientId) {
        return repository.findAllBySupplierId(clientId);
    }

}
