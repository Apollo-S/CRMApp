package crmapp.app.repositories;

import java.util.List;

import crmapp.app.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<Address, Integer> {

	@Query("SELECT a FROM Address a WHERE a.client.id = :clientId")
	List<Address> findAllByClientId(@Param("clientId") Integer clientId);

	@Query("SELECT a FROM Address a WHERE a.employee.id = :employeeId")
	List<Address> findAllByEmployeeId(@Param("employeeId") Integer employeeId);

	@Query("SELECT a FROM Address a WHERE a.ourCompany.id = :companyId")
	List<Address> findAllByCompanyId(@Param("companyId") Integer companyId);

}
