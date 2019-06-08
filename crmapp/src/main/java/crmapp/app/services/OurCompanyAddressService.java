package crmapp.app.services;

import crmapp.app.entities.OurCompanyAddress;
import crmapp.app.repositories.OurCompanyAddressRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OurCompanyAddressService extends ExtendedBaseServiceImpl<OurCompanyAddress, OurCompanyAddressRepository> {
}
