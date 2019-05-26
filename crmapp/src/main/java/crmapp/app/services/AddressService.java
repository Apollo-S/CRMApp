package crmapp.app.services;

import crmapp.app.entities.Address;
import crmapp.app.entities.Owner;
import crmapp.app.repositories.AddressRepository;
import crmapp.app.services.base.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService extends AbstractService<Address, AddressRepository> {

    public List<Address> findAllByContractorId(Integer id) {
        return repository.findAllByContractorId(id);
    }

    public Address save(int clientId, Address address) {
        return null;
    }

    public Address save(int id, Address address, Owner owner) {
        return null;
    }

    public Address updateWithClientId(int clientId, Address address) {
        return null;
    }

    public Address update(int id, Address address, Owner owner) {
        return null;
    }

}
