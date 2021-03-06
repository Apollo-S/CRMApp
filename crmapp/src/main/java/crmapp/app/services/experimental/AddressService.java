package crmapp.app.services.experimental;

import crmapp.app.entities.experimental.Address;
import crmapp.app.entities.Owner;
import crmapp.app.repositories.experimental.AddressRepository;
import crmapp.app.services.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService extends BaseServiceImpl<Address, AddressRepository> {

    public List<Address> findAllByContractorId(Integer id) {
        return null;
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
