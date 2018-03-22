package crmapp.app.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee_account")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class EmployeeAccount extends AbstractAccount {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@JsonBackReference(value = "employee-account")
	private Employee employee;


	public EmployeeAccount() {
	}

	public EmployeeAccount(Employee employee, String presentation, Date dateStart) {
		this.employee = employee;
		this.setPresentation(presentation);
		this.setDateStart(dateStart);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
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
