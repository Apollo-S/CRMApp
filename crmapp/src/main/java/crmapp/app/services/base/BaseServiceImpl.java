package crmapp.app.services.base;

import crmapp.app.entities.base.BaseEntity;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity, R extends BaseRepository<T, Integer>>
        implements BaseService<T, Integer> {

    @Autowired
    protected R repository;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Integer> findAllEntityIds() {
        return repository.findAllEntityIds();
    }

    @Override
    public T findById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public T save(T entity) {
        Date created = new Date();
        entity.setCreated(created);
        entity.setLastModified(created);
        entity = repository.save(entity);
        return entity;
    }

    @Override
    public void update(T entity) {
        int actualVersionNumber = repository.fetchValueByField(entity.getId(), "version");
        Date created = repository.fetchValueByField(entity.getId(), "created");
        entity.setVersion(actualVersionNumber);
        entity.setCreated(created);
        entity.setLastModified(new Date());
        repository.save(entity);
    }

    @Override
    public void update(Integer entityId, T entity) {
        entity.setId(entityId);
        this.update(entity);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }

}
