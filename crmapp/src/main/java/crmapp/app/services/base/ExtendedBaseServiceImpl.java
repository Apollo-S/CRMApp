package crmapp.app.services.base;

import crmapp.app.entities.BaseEntity;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class ExtendedBaseServiceImpl<T extends BaseEntity, R extends ExtendedBaseRepository<T, Integer>>
        extends BaseServiceImpl<T, R> implements ExtendedBaseService<T, Integer> {

    @Override
    public List<T> findAllFilterBy(String fieldName, Integer fieldId) {
        return this.repository.getAllFilterBy(fieldName, fieldId);
    }

}
