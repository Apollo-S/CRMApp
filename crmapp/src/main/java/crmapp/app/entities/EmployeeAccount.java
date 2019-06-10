package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractAccount;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.EMPLOYEE_ACCOUNTS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class EmployeeAccount extends AbstractAccount {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.EMPLOYEE_ID)
	@JsonBackReference(value = Tables.EMPLOYEE_ACCOUNTS)
	private Employee employee;


	public EmployeeAccount() {
	}

	public EmployeeAccount(String number, Bank bank, Date dateStart, Employee employee) {
		super(number, bank, dateStart);
		this.employee = employee;
	}

	@Override
	public String getUrl() {
		return employee.getUrl() + "/accounts/" + this.getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeAccount [");
		builder.append(super.toString()).append(", ");
		builder.append("employee=" + employee).append(", ");
		return builder.toString();
	}

}
