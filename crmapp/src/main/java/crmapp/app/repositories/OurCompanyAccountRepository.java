package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.OurCompanyAccount;

public interface OurCompanyAccountRepository extends BaseRepository<OurCompanyAccount, Integer> {

	@Query("SELECT oca FROM OurCompanyAccount oca WHERE oca.ourCompany.id = :companyId")
	public List<OurCompanyAccount> findAllByOurCompanyId(@Param("companyId") Integer companyId);
	
}
