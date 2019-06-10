package crmapp.app.repositories;

import crmapp.app.entities.Bank;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends BaseRepository<Bank, Integer> {
}
