package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.Vacation;

public interface VacationRepository extends BaseRepository<Vacation, Integer> {

	@Query("SELECT v FROM Vacation v WHERE v.employee.id = :employeeId")
	public List<Vacation> findAllVacationsByEmployeeId(@Param("employeeId") Integer employeeId);

}
