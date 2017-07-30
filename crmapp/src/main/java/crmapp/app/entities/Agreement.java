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
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "agreement")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Agreement extends UrlBaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contractor_id")
	@JsonBackReference(value = "contractor-agreement")
	private Contractor contractor;

	@Column(name = "number")
	private String number;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@Column(name = "comment")
	private String comment;

	public Agreement() {
	}

	public Agreement(Contractor contractor, String number, Date dateStart) {
		this.contractor = contractor;
		this.number = number;
		this.dateStart = dateStart;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
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
	
	@JsonInclude
	public Integer getContractorId() {
		return contractor.getId();
	}

}
