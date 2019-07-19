package crmapp.app.repositories;

import crmapp.app.entities.EmployeePost;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePostRepository extends ExtendedBaseRepository<EmployeePost, Integer> {
}
