package crmapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crmapp.app.entities.EmployeeAddress;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Integer> {

	@Query("SELECT ea FROM EmployeeAddress ea WHERE ea.employee.id = :employeeId")
	List<EmployeeAddress> findAllEmployeeAddressesByEmployeeId(@Param("employeeId") Integer employeeId);

}
