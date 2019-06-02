package crmapp.app.repositories;

import crmapp.app.entities.OurCompanyAccount;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OurCompanyAccountRepository extends ExtendedBaseRepository<OurCompanyAccount, Integer> {

	@Query("SELECT oca FROM OurCompanyAccount oca WHERE oca.ourCompany.id = :companyId")
	List<OurCompanyAccount> findAllByOurCompanyId(@Param("companyId") Integer companyId);
	
}
