package crmapp.app.services;

import crmapp.app.entities.Address;
import crmapp.app.entities.Owner;
import crmapp.app.entities.Client;
import crmapp.app.entities.Employee;
import crmapp.app.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService extends AbstractService<Address, AddressRepository> {

	@Autowired
	private ClientService clientService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private OurCompanyService ourCompanyService;

	public List<Address> findAllByClientId(Integer clientId) {
		return repository.findAllAddressesByClientId(clientId);
	}

	public Address save(int clientId, Address address) {
		Client client = clientService.findById(clientId);
		address.setClient(client);
		return this.save(address);
	}

	public Address save(int id, Address address, Owner owner) {
		switch (owner) {
			case CLIENT:
				Client client = clientService.findById(id);
				address.setClient(client);
				break;
			case EMPLOYEE:
				Employee employee = employeeService.findById(id);
				address.setEmployee(employee);
				break;
		}
		return this.save(address);
	}

	public Address updateWithClientId(int clientId, Address address) {
		Client client = clientService.findById(clientId);
		address.setClient(client);
		return this.update(address);
	}

	public Address update(int id, Address address, Owner owner) {
		switch (owner) {
			case CLIENT:
				Client client = clientService.findById(id);
				address.setClient(client);
				break;
			case EMPLOYEE:
				Employee employee = employeeService.findById(id);
				address.setEmployee(employee);
				break;
		}
		Client client = clientService.findById(id);
		address.setClient(client);
		return this.update(address);
	}

}
