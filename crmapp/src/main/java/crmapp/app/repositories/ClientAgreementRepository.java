package crmapp.app.repositories;

import crmapp.app.entities.ClientAddress;
import crmapp.app.entities.ClientAgreement;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAgreementRepository extends BaseRepository<ClientAgreement, Integer> {
}
