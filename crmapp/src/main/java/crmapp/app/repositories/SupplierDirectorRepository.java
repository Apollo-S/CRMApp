package crmapp.app.repositories;

import crmapp.app.entities.SupplierDirector;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierDirectorRepository extends BaseRepository<SupplierDirector, Integer>{

	@Query("SELECT sd FROM SupplierDirector sd WHERE sd.supplier.id = :supplierId")
	List<SupplierDirector> findAllBySupplierId(@Param("supplierId") Integer supplierId);
	
}


