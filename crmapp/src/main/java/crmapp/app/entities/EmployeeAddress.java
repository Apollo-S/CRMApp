package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.BaseEntity;
import crmapp.app.entities.experimental.Address;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.EMPLOYEE_ADDRESSES)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true,
	value = { "hibernateLazyInitializer", "handler" })
@NamedQuery(
		name = EmployeeAddress.FIND_ALL_ADDRESSES_BY_EMPLOYEE_ID,
		query = "SELECT e FROM EmployeeAddress e WHERE e.employee.id = ?1"
)
public class EmployeeAddress extends BaseEntity {

	public static final String FIND_ALL_ADDRESSES_BY_EMPLOYEE_ID = "findAllAddressesByEmployeeId";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.EMPLOYEE_ID)
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.ADDRESS_ID)
	private Address address;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	public EmployeeAddress() {
	}

	@Override
	public String getUrl() {
		return employee.getUrl() + "/addresses/" + this.getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeAddress [");
		builder.append(super.toString()).append(", ");
		builder.append("employee=" + employee).append("]");
		return builder.toString();
	}

}
