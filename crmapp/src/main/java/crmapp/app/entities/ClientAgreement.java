package crmapp.app.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "client_agreement")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler", "documents" })
public class ClientAgreement extends UrlBaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@JsonBackReference(value = "client-agreement")
	private Client client;

	@Column(name = "number")
	private String number;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;

	@Column(name = "comment")
	private String comment;

	@OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "agreement")
	@OrderBy("id ASC")
	@JsonManagedReference(value = "agreement-document")
	private Set<Document> documents;

	public ClientAgreement() {
	}

	public ClientAgreement(Client client, String number, Date dateStart) {
		this.client = client;
		this.number = number;
		this.dateStart = dateStart;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
	public Integer getClientId() {
		return client.getId();
	}

	@JsonInclude
	public String getClientAlias() {
		return client.getAlias();
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	@Override
	public String getUrl() {
		return "agreements/" + this.getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientAgreement [");
		builder.append(super.toString()).append(", ");
		builder.append("client=" + client).append(", ");
		builder.append("number=" + number).append(", ");
		builder.append("dateStart=" + dateStart).append("]");
		return builder.toString();
	}

}
