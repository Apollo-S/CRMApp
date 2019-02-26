package crmapp.app.services;

import java.util.List;

import crmapp.app.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import crmapp.app.entities.BaseEntity;

public abstract class AbstractService<T extends BaseEntity, R extends BaseRepository<T, Integer>>
		implements BaseService<T, Integer> {

	@Autowired
	protected R repository;

	@Override
	public List<T> findAll() {
		List<T> entities = repository.findAll();
		return entities;
	}

	@Override
	public List<Integer> findAllEntityIds() {
		return repository.findAllEntityIds();
	}

	@Override
	public T findById(Integer id) {
		T entity = repository.findOne(id);
		return entity;
	}

	@Override
	public T save(T entity) {
		T newEntity = null;
		if (entity != null) {
			entity = repository.save(entity);
			entity.setVersion(0);
			return entity;
		}
		return null;
	}

	@Override
	public T update(T entity) {
		int actualVersionNumber = repository.getOne(entity.getId()).getVersion();
		entity.setVersion(actualVersionNumber);
		entity = repository.save(entity);
		return entity;
	}

	@Override
	public T update(Integer entityId, T entity) {
		entity.setId(entityId);
		entity = this.update(entity);
		return entity;
	}

	@Override
	public void delete(Integer id) {
		repository.delete(id);
	}



}
