package crmapp.app.repositories;

import java.util.List;

import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.SickList;
import org.springframework.stereotype.Repository;

@Repository
public interface SickListRepository extends BaseRepository<SickList, Integer> {

	@Query("SELECT s FROM SickList s WHERE s.employee.id = :employeeId")
	List<SickList> findAllSickListsByEmployeeId(@Param("employeeId") Integer employeeId);

}
