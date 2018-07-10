package crmapp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.BaseEntity;
import crmapp.app.entities.Document;

public abstract class AbstractService<T, R extends JpaRepository<T, Integer>> implements BaseService<T> {

	@Autowired
	private R repository;

	@Override
	public List<T> getAll() {
		List<T> items = repository.findAll();
		return items;
	}

	@Override
	public T getById(int id) {
		T item = repository.findOne(id);
		return item;
	}

	@Override
	public T save(T entity) {
		((BaseEntity) entity).setVersion(0);
		entity = repository.save(entity);
		return entity;
	}

	@Override
	public T update(int id, T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
