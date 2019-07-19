package crmapp.app.services;

import crmapp.app.entities.Supplier;
import crmapp.app.entities.SupplierAddress;
import crmapp.app.repositories.SupplierAddressRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SupplierAddressService extends BaseServiceImpl<SupplierAddress, SupplierAddressRepository> {

    @Autowired
    private SupplierService supplierService;

    public SupplierAddress save(int supplierId, SupplierAddress address) {
        Supplier supplier = supplierService.findById(supplierId);
        address.setSupplier(supplier);
        return this.save(address);
    }

}
