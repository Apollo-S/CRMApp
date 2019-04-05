package crmapp.app.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Tables.EMPLOYEE_ACCOUNTS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class EmployeeAccount extends AbstractAccount {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@JsonBackReference(value = Tables.EMPLOYEE_ACCOUNTS)
	private Employee employee;


	public EmployeeAccount() {
	}

	public EmployeeAccount(String number, String bankName, String mfo, Date dateStart, Employee employee) {
		super(number, bankName, mfo, dateStart);
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
