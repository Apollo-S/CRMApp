package crmapp.app.repositories;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

@SuppressWarnings("unchecked")
public class ExtendedRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements BaseRepository<T, ID> {

	private JpaEntityInformation<T, ?> entityInformation;
	private EntityManager entityManager;

	public ExtendedRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

//	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByIds(ID... ids) {
		String qlString = "select e from " + this.entityInformation.getEntityName() + 
				" e where e." + this.entityInformation.getIdAttribute().getName() + " in :ids";
		Query query = this.entityManager.createQuery(qlString);
		query.setParameter("ids", Arrays.asList(ids));
		return (List<T>) query.getResultList();
	}

	@Override 
	public List<Integer> findAllEntityIds() {
		String qlString = "select e.id from " + this.entityInformation.getEntityName() + " e";
		Query query = this.entityManager.createQuery(qlString);
		return (List<Integer>) query.getResultList();
	}

}
