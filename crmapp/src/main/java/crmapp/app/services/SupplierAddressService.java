package crmapp.app.services;

import crmapp.app.entities.Supplier;
import crmapp.app.entities.SupplierAddress;
import crmapp.app.repositories.SupplierAddressRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SupplierAddressService extends AbstractService<SupplierAddress, SupplierAddressRepository> {

    @Autowired
    private SupplierService supplierService;

    public List<SupplierAddress> findAllByClientId(Integer clientId) {
        return repository.findAllBySupplierId(clientId);
    }

    public SupplierAddress save(int supplierId, SupplierAddress address) {
        Supplier supplier = supplierService.findById(supplierId);
        address.setSupplier(supplier);
        return this.save(address);
    }

    public SupplierAddress updateWithClientId(int supplierId, SupplierAddress address) {
        Supplier supplier = supplierService.findById(supplierId);
        address.setSupplier(supplier);
        return this.update(address);
    }

}
