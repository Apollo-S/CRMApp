package crmapp.app.repositories;

import crmapp.app.entities.Employee;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Integer> {

}
