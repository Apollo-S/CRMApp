package crmapp.app.services;

import crmapp.app.entities.EmployeeAccount;
import crmapp.app.repositories.EmployeeAccountRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAccountService extends AbstractService<EmployeeAccount, EmployeeAccountRepository> {
}
