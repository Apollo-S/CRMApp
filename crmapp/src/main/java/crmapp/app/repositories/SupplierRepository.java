package crmapp.app.repositories;

import crmapp.app.entities.Supplier;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends BaseRepository<Supplier, Integer> {
}
