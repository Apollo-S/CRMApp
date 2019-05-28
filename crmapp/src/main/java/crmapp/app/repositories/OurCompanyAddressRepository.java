package crmapp.app.repositories;

import crmapp.app.entities.OurCompanyAddress;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OurCompanyAddressRepository extends BaseRepository<OurCompanyAddress, Integer> {

    @Query("SELECT oca FROM OurCompanyAddress oca WHERE oca.ourCompany.id = :ourCompanyId")
    List<OurCompanyAddress> findAllByOurCompanyId(@Param("ourCompanyId") Integer ourCompanyId);

}
