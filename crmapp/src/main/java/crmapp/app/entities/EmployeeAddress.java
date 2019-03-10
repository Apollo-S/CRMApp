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
@Table(name = "employee_address")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class EmployeeAddress extends Address {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@JsonBackReference(value = "employee-address")
	private Employee employee;

	public EmployeeAddress() {
	}

	public EmployeeAddress(Employee employee, Date dateStart) {
		this.employee = employee;
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
