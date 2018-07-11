package crmapp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.BaseEntity;

public abstract class AbstractService<T extends BaseEntity, R extends JpaRepository<T, Integer>>
		implements BaseService<T> {

	@Autowired
	protected R repository;

	@Override
	public List<T> getAll() {
		List<T> entities = repository.findAll();
		return entities;
	}

	@Override
	public T getById(int id) {
		T entity = repository.findOne(id);
		return entity;
	}

	@Override
	public T save(T entity) {
		entity.setVersion(0);
		entity = repository.save(entity);
		return entity;
	}

	@Override
	public T update(int id, T entity) {
		entity.setId(id);
		int actualVersionNumber = repository.getOne(id).getVersion();
		entity.setVersion(actualVersionNumber);
		entity = repository.save(entity);
		return entity;
	}

	@Override
	public void delete(int id) {
		repository.delete(id);
	}

}
