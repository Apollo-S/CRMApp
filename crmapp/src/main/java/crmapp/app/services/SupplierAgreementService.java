package crmapp.app.services;

import crmapp.app.entities.SupplierAgreement;
import crmapp.app.repositories.SupplierAgreementRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierAgreementService extends BaseServiceImpl<SupplierAgreement, SupplierAgreementRepository> {

    public List<SupplierAgreement> findAllBySupplierId(int clientId) {
        return repository.findAllBySupplierId(clientId);
    }

}
