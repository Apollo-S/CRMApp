package crmapp.app.services;

import crmapp.app.entities.Supplier;
import crmapp.app.entities.SupplierAccount;
import crmapp.app.repositories.SupplierAccountRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SupplierAccountService extends BaseServiceImpl<SupplierAccount, SupplierAccountRepository> {

    @Autowired
    private SupplierService supplierService;

    public SupplierAccount save(int supplierId, SupplierAccount account) {
        Supplier supplier = supplierService.findById(supplierId);
        account.setSupplier(supplier);
        account = this.save(account);
        return account;
    }

}
