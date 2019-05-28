package crmapp.app.repositories;

import java.util.List;

import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import crmapp.app.entities.OurCompanyDirector;
import org.springframework.stereotype.Repository;

@Repository
public interface OurCompanyDirectorRepository extends BaseRepository<OurCompanyDirector, Integer> {

	@Query("SELECT ocd FROM OurCompanyDirector ocd WHERE ocd.ourCompany.id = :companyId")
	List<OurCompanyDirector> findAllOurCompanyDirectorsByCompanyId(@Param("companyId") Integer companyId);

}