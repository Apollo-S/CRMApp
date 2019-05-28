package crmapp.app.repositories;

import crmapp.app.repositories.base.BaseRepository;
import crmapp.app.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Integer> {

}
