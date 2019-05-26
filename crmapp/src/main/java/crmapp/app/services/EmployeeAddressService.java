package crmapp.app.services;

import crmapp.app.entities.Employee;
import crmapp.app.entities.EmployeeAddress;
import crmapp.app.repositories.EmployeeAddressRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeAddressService extends AbstractService<EmployeeAddress, EmployeeAddressRepository> {

	@Autowired
	private EmployeeService employeeService;

	public List<EmployeeAddress> findAllByEmployeeId(Integer employeeId) {
		return repository.findAllByEmployeeId(employeeId);
	}

	public EmployeeAddress save(int employeeId, EmployeeAddress address) {
		Employee employee = employeeService.findById(employeeId);
		address.setEmployee(employee);
		return this.save(address);
	}

	public EmployeeAddress updateWithEmployeeId(int employeeId, EmployeeAddress address) {
		Employee employee = employeeService.findById(employeeId);
		address.setEmployee(employee);
		return this.update(address);
	}

}
