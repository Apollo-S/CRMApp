package crmapp.app.services;

import crmapp.app.entities.Supplier;
import crmapp.app.repositories.SupplierRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SupplierService extends BaseServiceImpl<Supplier, SupplierRepository> {
}
