package crmapp.app.services;

import java.util.List;

public interface BaseService<T> {

	public List<T>getAll();
	public T getById(int id);
	public T save(T entity);
	public T update(int id, T entity);
	public void delete(int id);
	
}
