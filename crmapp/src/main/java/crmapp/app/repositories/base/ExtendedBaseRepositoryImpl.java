package crmapp.app.repositories.base;

import crmapp.app.entities.BaseEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public class ExtendedBaseRepositoryImpl<T, Z extends BaseEntity, ID extends Serializable> extends ExtendedRepositoryImpl<T, ID>
        implements ExtendedBaseRepository<T, Z, ID> {

    public ExtendedBaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    public List<T> findAllSubEntitiesByMainEntity(Z mainEntity) {
        String qlString = "select e from " + getEntityInformation().getEntityName() +
                " e where e." + mainEntity.getClass().getSimpleName().toLowerCase() + ".id = " + mainEntity.getId();
        Query query = getEntityManager().createQuery(qlString);
        return (List<T>) query.getResultList();
//        "SELECT ca FROM ClientAccount ca WHERE ca.client.id = :clientId"
    }

}
