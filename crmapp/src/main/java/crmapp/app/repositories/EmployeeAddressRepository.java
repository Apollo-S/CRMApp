package crmapp.app.repositories;

import crmapp.app.entities.EmployeeAddress;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAddressRepository extends ExtendedBaseRepository<EmployeeAddress, Integer> {
}
