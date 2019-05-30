package crmapp.app.repositories.base;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class ExtendedBaseRepositoryImpl<T, ID extends Serializable> extends BaseRepositoryImpl<T, ID>
        implements ExtendedBaseRepository<T, ID> {

    public ExtendedBaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public List<T> getAllFilterBy(String fieldName, ID fieldId) {
        String qlString = "select e from " + super.entityInformation.getEntityName() +
                " e where e." + fieldName + ".id = " + fieldId;
        Query query = super.entityManager.createQuery(qlString);
        return (List<T>) query.getResultList();
    }

}
