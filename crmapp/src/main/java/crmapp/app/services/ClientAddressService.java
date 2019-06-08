package crmapp.app.services;

import crmapp.app.entities.ClientAddress;
import crmapp.app.repositories.ClientAddressRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ClientAddressService extends ExtendedBaseServiceImpl<ClientAddress, ClientAddressRepository> {
}
