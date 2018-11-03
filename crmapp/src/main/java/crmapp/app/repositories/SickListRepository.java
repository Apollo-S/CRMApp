package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.SickList;

public interface SickListRepository extends JpaRepository<SickList, Integer> {

	@Query("SELECT s FROM SickList s WHERE s.employee.id = :employeeId")
	List<SickList> findAllSickListsByEmployeeId(@Param("employeeId") Integer employeeId);

}
