package crmapp.app.repositories;

import crmapp.app.entities.Supplier;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends BaseRepository<Supplier, Integer> {
}
