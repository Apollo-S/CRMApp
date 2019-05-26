package crmapp.app.repositories;

import crmapp.app.entities.EmployeeAddress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeAddressRepository extends BaseRepository<EmployeeAddress, Integer> {

    @Query("SELECT ea FROM EmployeeAddress ea WHERE ea.employee.id = :employeeId")
    List<EmployeeAddress> findAllByEmployeeId(@Param("employeeId") Integer employeeId);

}
