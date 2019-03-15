package crmapp.app.repositories;

import crmapp.app.entities.AgreementType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementTypeRepository extends BaseRepository<AgreementType, Integer>{

}
