package crmapp.app.services.base;

import java.util.List;

public interface ExtendedBaseService<T, ID> extends BaseService<T, ID> {

	List<T> findAllFilterBy(String fieldName, ID fieldId);

}
