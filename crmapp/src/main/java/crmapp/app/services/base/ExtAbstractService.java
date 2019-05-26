package crmapp.app.services.base;

import crmapp.app.entities.BaseEntity;
import crmapp.app.repositories.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public abstract class ExtAbstractService<T extends BaseEntity, R extends BaseRepository<T, Integer>>
		extends AbstractService<T, R> implements ExtendedBaseService<T, Integer> {

	public List<T> getAllEntitiesByMainEntityId(Integer mainEntityId) {
		return null; //todo implementation
	}

}
