package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
