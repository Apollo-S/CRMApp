package crmapp.app.repositories.base;

import crmapp.app.entities.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ExtendedBaseRepository<T, Z extends BaseEntity, ID extends Serializable> extends BaseRepository<T, ID> {

	List<T> findAllSubEntitiesByMainEntity(Z mainEntity);
	
}
