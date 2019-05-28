package crmapp.app.repositories;

import crmapp.app.entities.SupplierAccount;
import crmapp.app.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierAccountRepository extends BaseRepository<SupplierAccount, Integer> {

	@Query("SELECT sa FROM SupplierAccount sa WHERE sa.supplier.id = :supplierId")
	List<SupplierAccount> findAllBySupplierId(@Param("supplierId") Integer supplierId);
	
}


