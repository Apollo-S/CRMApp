package crmapp.app.services.base;

import java.util.List;

public interface BaseService<T, ID> {

	List<T> findAll();
	T findById(ID id);
	T save(T entity);
	void update(T entity);
	void update(ID id, T entity);
	void delete(ID id);
	List<ID> findAllEntityIds();
	List<T> findAllByNamedQuery(String queryName, Class<T> entityClass, Object ... params);

}
