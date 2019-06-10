package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import crmapp.app.entities.base.AbstractAddress;
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
public class EmployeeAddress extends AbstractAddress {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.EMPLOYEE_ID)
	@JsonBackReference(value = Tables.EMPLOYEE_ADDRESSES)
	private Employee employee;

	public EmployeeAddress() {
	}

	public EmployeeAddress(Employee employee, Date dateStart) {
		this.employee = employee;
		this.setDateStart(dateStart);
	}

	@JsonInclude
	public Employee getClientInfo() {
		return employee;
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
