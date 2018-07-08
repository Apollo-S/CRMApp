package crmapp.app.services;

import java.util.List;

public interface BaseService<T> {

	public List<T>getAll();
	public T getById(int id);
	public T save(T entity);
	
}
