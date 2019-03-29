package crmapp.app.services;

import crmapp.app.entities.Client;
import crmapp.app.entities.ClientAddress;
import crmapp.app.entities.ClientAgreement;
import crmapp.app.repositories.ClientAddressRepository;
import crmapp.app.repositories.ClientAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientAgreementService extends AbstractService<ClientAgreement, ClientAgreementRepository> {
}
