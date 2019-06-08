package crmapp.app.repositories.base;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {

    protected JpaEntityInformation<T, ?> entityInformation;
    protected EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }

    @Override
    public List<T> findByIds(ID... ids) {
        String qlString = "select e from " + this.entityInformation.getEntityName() +
                " e where e." + this.entityInformation.getIdAttribute().getName() + " in :ids";
        Query query = this.entityManager.createQuery(qlString);
        query.setParameter("ids", Arrays.asList(ids));
        return (List<T>) query.getResultList();
    }

    @Override
    public List<ID> findAllEntityIds() {
        String qlString = "select e.id from " + this.entityInformation.getEntityName() + " e";
        Query query = this.entityManager.createQuery(qlString);
        return (List<ID>) query.getResultList();
    }

    @Override
    public Integer fetchVersion(ID entityId) {
        String qlString = "select e.version from " + this.entityInformation.getEntityName() +
                " e where e." + this.entityInformation.getIdAttribute().getName() + " = " + entityId;
        Query query = this.entityManager.createQuery(qlString);
        return (Integer) query.getSingleResult();
    }

}
