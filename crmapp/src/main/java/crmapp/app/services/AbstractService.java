package crmapp.app.services;

import java.io.Serializable;
import java.util.List;

import crmapp.app.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import crmapp.app.entities.BaseEntity;

public abstract class AbstractService<T extends BaseEntity, R extends BaseRepository<T, Integer>>
		implements BaseService<T, Integer> {

	@Autowired
	protected R repository;

	@Override
	public List<T> getAll() {
		List<T> entities = repository.findAll();
		return entities;
	}

	@Override
	public List<Integer> findAllEntityIds() {
		return repository.findAllEntityIds();
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
	public T update(T entity) {
		int actualVersionNumber = repository.getOne(entity.getId()).getVersion();
		entity.setVersion(actualVersionNumber);
		entity = repository.save(entity);
		return entity;
	}

	@Override
	public T update(int entityId, T entity) {
		entity.setId(entityId);
		entity = this.update(entity);
		return entity;
	}

	@Override
	public void delete(int id) {
		repository.delete(id);
	}



}
