package crmapp.app.repositories;

import java.util.List;

import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.Vacation;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends BaseRepository<Vacation, Integer> {

	@Query("SELECT v FROM Vacation v WHERE v.employee.id = :employeeId")
	List<Vacation> findAllVacationsByEmployeeId(@Param("employeeId") Integer employeeId);

}
