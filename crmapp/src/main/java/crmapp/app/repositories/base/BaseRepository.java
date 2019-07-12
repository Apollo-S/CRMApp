package crmapp.app.repositories.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	@SuppressWarnings("unchecked")
	List<T> findByIds(ID... ids);
	
	List<ID> findAllEntityIds();

	Integer fetchVersion(ID entityId);

	<U> U fetchValueByField(ID entityId, String fieldName);
	
}
