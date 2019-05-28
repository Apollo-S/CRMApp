package crmapp.app.repositories;

import java.util.List;

import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.OurCompanyAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface OurCompanyAccountRepository extends BaseRepository<OurCompanyAccount, Integer> {

	@Query("SELECT oca FROM OurCompanyAccount oca WHERE oca.ourCompany.id = :companyId")
	List<OurCompanyAccount> findAllByOurCompanyId(@Param("companyId") Integer companyId);
	
}
