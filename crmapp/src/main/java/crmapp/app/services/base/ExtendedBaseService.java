package crmapp.app.services.base;

import crmapp.app.entities.AbstractCompany;
import crmapp.app.entities.base.Contractorable;

import java.util.List;

public interface ExtendedBaseService<T, ID> extends BaseService<T, ID> {

	List<T> findAllFilterBy(String fieldName, Integer fieldId);
	<S extends ExtendedBaseService, C extends AbstractCompany, E extends Contractorable> T saveWith(
			int mainEntityId, E entity, S additionalService);

}
