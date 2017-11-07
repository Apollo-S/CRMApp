package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.EmployeeAccount;

public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, Integer>{

	@Query("SELECT ea FROM EmployeeAccount ea WHERE ea.employee.id = :employeeId")
	public List<EmployeeAccount> findAllByEmployeeId(@Param("employeeId") Integer employeeId);
	
}


