package crmapp.app.repositories.base;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ExtendedBaseRepository<T, ID extends Serializable> extends BaseRepository<T, ID> {

	List<T> getAllFilterBy(String fieldName, ID fieldId);
	
}
