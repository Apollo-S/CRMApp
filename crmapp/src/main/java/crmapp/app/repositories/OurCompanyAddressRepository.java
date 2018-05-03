package crmapp.app.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import crmapp.app.entities.OurCompanyAddress;

public interface OurCompanyAddressRepository extends BaseRepository<OurCompanyAddress, Integer> {

	@Query("SELECT oca FROM OurCompanyAddress oca WHERE oca.ourCompany.id = :companyId")
	public List<OurCompanyAddress> findAllOurCompanyAddressesByCompanyId(@Param("companyId") Integer companyId);

}
