package crmapp.app.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class AbstractAccount extends BaseEntity {

	@Column(name = "presentation", length = 255)
	private String presentation;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

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
	public int hashCode() {
		return Objects.hash(this.getId(), this.getPresentation(), this.getDateStart());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractAccount that = (AbstractAccount) obj;
		return Objects.equals(this.getId(), that.getId()) && 
				Objects.equals(this.getPresentation(), that.getPresentation()) && 
				Objects.equals(this.getDateStart(), that.getDateStart());
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(super.toString()).append(", ")
			.append("presentation=" + presentation).append(", ")
			.append("dateStart=" + dateStart)
			.toString();
	}

}
