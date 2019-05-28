package crmapp.app.repositories;

import crmapp.app.entities.SupplierAddress;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAddressRepository extends BaseRepository<SupplierAddress, Integer> {

    @Query("SELECT sa FROM SupplierAddress sa WHERE sa.supplier.id = :supplierId")
    List<SupplierAddress> findAllBySupplierId(@Param("supplierId") Integer supplierId);

}
