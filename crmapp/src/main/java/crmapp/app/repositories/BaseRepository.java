package crmapp.app.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	@SuppressWarnings("unchecked")
	public List<T> findByIds(ID...ids);
	
	public List<Integer> findAllIds();
	
}
