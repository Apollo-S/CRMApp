package crmapp.app.services;

import crmapp.app.entities.EmployeeAddress;
import crmapp.app.repositories.EmployeeAddressRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAddressService extends ExtendedBaseServiceImpl<EmployeeAddress, EmployeeAddressRepository> {
}
