package crmapp.app.services.base;

import crmapp.app.entities.BaseEntity;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, R extends BaseRepository<T, Integer>>
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
		entity = repository.save(entity);
		entity.setVersion(0);
		return entity;
	}

	@Override
	public T update(T entity) {
		int actualVersionNumber = repository.fetchVersion(entity.getId());
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