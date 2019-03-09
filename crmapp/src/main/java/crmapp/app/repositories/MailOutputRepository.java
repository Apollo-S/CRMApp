package crmapp.app.repositories;

import org.springframework.data.jpa.repository.Query;

import crmapp.app.entities.MailOutput;
import org.springframework.stereotype.Repository;

@Repository
public interface MailOutputRepository extends BaseRepository<MailOutput, Integer> {

	@Query("select coalesce(max(m.number),0) from MailOutput m")
	int getMaxMailOutputNumber();
	
}
