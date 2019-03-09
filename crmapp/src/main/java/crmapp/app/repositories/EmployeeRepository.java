package crmapp.app.repositories;

import crmapp.app.entities.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Integer> {

}
