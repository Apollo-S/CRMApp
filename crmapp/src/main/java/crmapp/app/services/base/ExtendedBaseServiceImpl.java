package crmapp.app.services.base;

import crmapp.app.entities.base.BaseEntity;
import crmapp.app.repositories.base.ExtendedBaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static crmapp.app.services.Utils.convertEntityToDTO;

@Transactional
public abstract class ExtendedBaseServiceImpl<T extends BaseEntity, R extends ExtendedBaseRepository<T, Integer>>
        extends BaseServiceImpl<T, R> implements ExtendedBaseService<T, Integer> {

    @Override
    public List<T> findAllFilterBy(String fieldName, Integer fieldId) {
        return this.repository.getAllFilterBy(fieldName, fieldId);
    }

    public <U> List<U> findAllFilterBy(String fieldName, Integer fieldId, Class<U> classDTO) {
        List<T> entities = this.repository.getAllFilterBy(fieldName, fieldId);
        return convertEntityToDTO(entities, classDTO);
    }

}
