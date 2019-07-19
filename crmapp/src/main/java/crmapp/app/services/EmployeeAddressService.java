package crmapp.app.services;

import crmapp.app.entities.Employee;
import crmapp.app.entities.EmployeeAddress;
import crmapp.app.repositories.EmployeeAddressRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeAddressService extends BaseServiceImpl<EmployeeAddress, EmployeeAddressRepository> {

	@Autowired
	private EmployeeService employeeService;

	public EmployeeAddress save(int employeeId, EmployeeAddress address) {
		Employee employee = employeeService.findById(employeeId);
		address.setEmployee(employee);
		return this.save(address);
	}

}
