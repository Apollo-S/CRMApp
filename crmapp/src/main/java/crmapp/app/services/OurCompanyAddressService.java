package crmapp.app.services;

import crmapp.app.entities.OurCompany;
import crmapp.app.entities.OurCompanyAddress;
import crmapp.app.repositories.OurCompanyAddressRepository;
import crmapp.app.services.base.ExtendedBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OurCompanyAddressService extends ExtendedBaseServiceImpl<OurCompanyAddress, OurCompanyAddressRepository> {

	@Autowired
	private OurCompanyService ourCompanyService;

	public List<OurCompanyAddress> findAllByCompanyId(Integer ourCompanyId) {
		return super.repository.findAllByOurCompanyId(ourCompanyId);
	}

	public OurCompanyAddress save(int ourCompanyId, OurCompanyAddress address) {
		OurCompany ourCompany = ourCompanyService.findById(ourCompanyId);
		address.setOurCompany(ourCompany);
		return this.save(address);
	}

	public OurCompanyAddress updateWithCompanyId(int ourCompanyId, OurCompanyAddress address) {
		OurCompany ourCompany = ourCompanyService.findById(ourCompanyId);
		address.setOurCompany(ourCompany);
		return this.update(address);
	}

}
