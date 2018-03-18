package crmapp.app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee_address")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class EmployeeAddress extends UrlBaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@JsonBackReference(value = "employee-address")
	private Employee employee;

	@Column(name = "presentation", length = 255)
	private String presentation;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	public EmployeeAddress() {
	}

	public EmployeeAddress(Employee employee, String presentation, Date dateStart) {
		this.employee = employee;
		this.presentation = presentation;
		this.dateStart = dateStart;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
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
		builder.append("employee=" + employee).append(", ");
		builder.append("presentation=" + presentation).append(", ");
		builder.append("dateStart=" + dateStart).append("]");
		return builder.toString();
	}

}
