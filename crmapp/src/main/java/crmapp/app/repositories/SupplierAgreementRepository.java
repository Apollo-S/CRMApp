package crmapp.app.repositories;

import crmapp.app.entities.SupplierAgreement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAgreementRepository extends BaseRepository<SupplierAgreement, Integer> {

    @Query("SELECT sa FROM SupplierAgreement sa WHERE sa.supplier.id = :supplierId")
    List<SupplierAgreement> findAllBySupplierId(@Param("supplierId") Integer supplierId);

}
