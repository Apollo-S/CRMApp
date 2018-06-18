package crmapp.app.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class AbstractAgreement extends BaseEntity {

	@Column(name = "number", length = 255)
	private String number;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@Column(name = "comment", length = 255)
	private String comment;

	public AbstractAgreement() {
	}

	public AbstractAgreement(String number, Date dateStart, String comment) {
		this.number = number;
		this.dateStart = dateStart;
		this.comment = comment;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.getNumber(), this.getDateStart(), this.getVersion());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractAgreement that = (AbstractAgreement) obj;
		return Objects.equals(this.getId(), that.getId()) && 
				Objects.equals(this.getNumber(), that.getNumber()) && 
				Objects.equals(this.getDateStart(), that.getDateStart()) && 
				Objects.equals(this.getVersion(), that.getVersion());
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(super.toString()).append(", ")
			.append("number=" + number).append(", ")
			.append("dateStart=" + dateStart)
			.toString();
	}

}
