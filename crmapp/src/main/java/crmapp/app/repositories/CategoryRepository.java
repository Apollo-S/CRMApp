package crmapp.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import crmapp.app.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer>{

}
