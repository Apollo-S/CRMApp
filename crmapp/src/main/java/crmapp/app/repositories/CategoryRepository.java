package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import crmapp.app.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
