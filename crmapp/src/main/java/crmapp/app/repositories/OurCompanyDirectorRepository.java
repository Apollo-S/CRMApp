package crmapp.app.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import crmapp.app.entities.OurCompanyDirector;

public interface OurCompanyDirectorRepository extends BaseRepository<OurCompanyDirector, Integer> {

	@Query("SELECT ocd FROM OurCompanyDirector ocd WHERE ocd.ourCompany.id = :companyId")
	List<OurCompanyDirector> findAllOurCompanyDirectorsByCompanyId(@Param("companyId") Integer companyId);

}