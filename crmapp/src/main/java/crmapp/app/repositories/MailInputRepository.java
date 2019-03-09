package crmapp.app.repositories;

import org.springframework.data.jpa.repository.Query;
import crmapp.app.entities.MailInput;
import org.springframework.stereotype.Repository;

@Repository
public interface MailInputRepository extends BaseRepository<MailInput, Integer> {

	@Query("select coalesce(max(m.number),0) from MailInput m")
	int getMaxMailInputNumber();
	
}
