package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import crmapp.app.entities.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = Tables.VACATIONS)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class Vacation extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.EMPLOYEE_ID)
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

	@JsonInclude
	public String getFullPeriod() {
		Format formatter = new SimpleDateFormat(DATE_FORMAT);
		String startDate = formatter.format(this.dateStart);
		String dateFinal = formatter.format(this.dateFinal);
		return startDate + PERIOD_SEPARATOR + dateFinal;
	}

	@JsonInclude
	public Integer getEmployeeId() {
		return this.employee.getId();
	}

	@JsonInclude
	public String getEmployeeShortName() {
		return this.employee.getPerson().getShortName();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vacation [");
		builder.append(super.toString()).append(", ");
		builder.append("employee=" + employee.getPerson().getShortName()).append(", ");
		builder.append("description=" + description).append(", ");
		builder.append("period=" + this.getFullPeriod()).append(", ");
		builder.append("daysAmount=" + daysAmount).append(", ");
		builder.append("holidayAmount=" + holidayAmount).append(", ");
		builder.append("comment=" + comment).append("]");
		return builder.toString();
	}

}
