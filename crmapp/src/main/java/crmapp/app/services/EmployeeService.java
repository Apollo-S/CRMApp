package crmapp.app.services;

import crmapp.app.entities.Employee;
import crmapp.app.repositories.EmployeeRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeService extends BaseServiceImpl<Employee, EmployeeRepository> {

}
