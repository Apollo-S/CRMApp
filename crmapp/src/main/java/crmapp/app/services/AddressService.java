package crmapp.app.services;

import crmapp.app.entities.*;
import crmapp.app.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public List<Address> findAllByOwnerId(Integer id, Owner owner) {
        switch (owner) {
            case CLIENT:
                return repository.findAllByClientId(id);
            case EMPLOYEE:
                return repository.findAllByEmployeeId(id);
            case OUR_COMPANY:
                return repository.findAllByCompanyId(id);
            default:
                return new ArrayList<>();
        }
    }

    public Address save(int clientId, Address address) {
        Client client = clientService.findById(clientId);
        address.setClient(client);
        return this.save(address);
    }

    public Address save(int id, Address address, Owner owner) {
        switch (owner) {
            case CLIENT:
                address.setClient(clientService.findById(id));
                break;
            case EMPLOYEE:
                address.setEmployee(employeeService.findById(id));
                break;
            case OUR_COMPANY:
                address.setOurCompany(ourCompanyService.findById(id));
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
                address.setClient(clientService.findById(id));
                break;
            case EMPLOYEE:
                address.setEmployee(employeeService.findById(id));
                break;
            case OUR_COMPANY:
                address.setOurCompany(ourCompanyService.findById(id));
                break;
        }
        return this.update(address);
    }

}
