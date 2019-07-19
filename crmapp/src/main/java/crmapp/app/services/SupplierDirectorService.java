package crmapp.app.services;

import crmapp.app.entities.SupplierDirector;
import crmapp.app.repositories.SupplierDirectorRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SupplierDirectorService extends BaseServiceImpl<SupplierDirector, SupplierDirectorRepository> {

    @Autowired
    private SupplierService supplierService;

    public SupplierDirector save(int supplierId, SupplierDirector director) {
        director.setSupplier(supplierService.findById(supplierId));
        return this.save(director);
    }

}
