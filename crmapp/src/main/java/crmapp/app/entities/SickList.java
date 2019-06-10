package crmapp.app.entities;

import java.text.Format;
import java.text.SimpleDateFormat;
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
import crmapp.app.entities.base.BaseEntity;

@Entity
@Table(name = "sick_lists")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class SickList extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	@JsonBackReference(value = "employee-sicklist")
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

	@Column(name = "comment", length = 255)
	private String comment;

	public SickList() {
	}

	public SickList(Employee employee, String description, Date dateStart, Date dateFinal, Integer daysAmount,
			String comment) {
		this.employee = employee;
		this.description = description;
		this.dateStart = dateStart;
		this.dateFinal = dateFinal;
		this.daysAmount = daysAmount;
		this.comment = comment;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setDaysAmount(Integer daysAmount) {
		this.daysAmount = daysAmount;
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
		return this.employee.getPerson().getShortName();
	}
	
	@JsonInclude
	public String getFullPeriod() {
		Format formatter = new SimpleDateFormat(DATE_FORMAT);
		String dateStart = formatter.format(this.dateStart);
		String dateFinal = formatter.format(this.dateFinal);
		return dateStart + PERIOD_SEPARATOR + dateFinal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SickList [");
		builder.append(super.toString()).append(", ");
		builder.append("employee=" + employee).append(", ");
		builder.append("description=" + description).append(", ");
		builder.append("period=" + this.getFullPeriod()).append(", ");
		builder.append("daysAmount=" + daysAmount).append(", ");
		builder.append("comment=" + comment).append("]");
		return builder.toString();
	}

}
