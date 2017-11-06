package crmapp.app.entities;

import java.io.Serializable;
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
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "vacation")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class Vacation extends UrlBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@JsonBackReference(value = "employee-vacation")
	private Employee employee;

	@Column(name = "description", length = 100)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_final")
	private Date dateFinal;

	@Column(name = "days_amount")
	private Integer daysAmount;

	@Column(name = "holiday_amount")
	private Integer holidayAmount;

	@Column(name = "comment", length = 255)
	private String comment;

	public Vacation() {
	}

	public Vacation(Employee employee, String description, Date dateStart, Date dateFinal, Integer daysAmount,
			Integer holidayAmount, String comment) {
		this.employee = employee;
		this.description = description;
		this.dateStart = dateStart;
		this.dateFinal = dateFinal;
		this.daysAmount = daysAmount;
		this.holidayAmount = holidayAmount;
		this.comment = comment;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateFinal() {
		return dateFinal;
	}

	public void setDateFinal(Date dateFinal) {
		this.dateFinal = dateFinal;
	}

	public Integer getDaysAmount() {
		return daysAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDaysAmount(Integer daysAmount) {
		this.daysAmount = daysAmount;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHolidayAmount() {
		return holidayAmount;
	}

	public void setHolidayAmount(Integer holidayAmount) {
		this.holidayAmount = holidayAmount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@JsonInclude
	public Integer getEmployeeId() {
		return this.employee.getId();
	}

	@JsonInclude
	public String getEmployeeShortName() {
		return this.employee.getShortName();
	}

}
