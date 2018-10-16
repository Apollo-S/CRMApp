package crmapp.app.services;

import java.util.List;

public interface BaseService<T, ID> {

	List<T> getAll();
	T getById(int id);
	T save(T entity);
	T update(T entity);
	T update(int id, T entity);
	void delete(int id);
	List<ID> findAllEntityIds();

}
