package crmapp.app.repositories;

import java.util.List;

import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.EmployeeAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAccountRepository extends BaseRepository<EmployeeAccount, Integer> {

	@Query("SELECT ea FROM EmployeeAccount ea WHERE ea.employee.id = :employeeId")
	List<EmployeeAccount> findAllByEmployeeId(@Param("employeeId") Integer employeeId);
	
}


