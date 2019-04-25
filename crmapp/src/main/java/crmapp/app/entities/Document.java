package crmapp.app.entities;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Tables.DOCUMENTS)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true,
	value = { "hibernateLazyInitializer", "handler" })
public class Document extends BaseEntity {

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_type_id")
	private DocumentType docType;

	@Column(name = "number")
	private String number;

	@Column(name = "amount")
	private Double amount;

	@Temporal(TemporalType.DATE)
	@Column(name = "dated")
	private Date dated;

	@Temporal(TemporalType.DATE)
	@Column(name = "payment_date")
	private Date paymentDate;

	@Column(name = "comment")
	private String comment;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_status_id")
	private DocumentStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agreement_id")
	@JsonBackReference(value = "agreement-document")
	private Agreement agreement;

    @Temporal(TemporalType.DATE)
    @Column(name = "passing_date")
    private Date passingDate;

	public Document() {
	}

	public Document(Double amount, Date paymentDate, DocumentType docType, DocumentStatus status, String comment) {
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.docType = docType;
		this.status = status;
		this.comment = comment;
	}

    @JsonInclude
	public Integer getAgreementId() {
		return agreement.getId();
	}

	@JsonInclude
	public String getAgreementNumber() {
		return agreement.getNumber();
	}

	@JsonInclude
	public Date getAgreementDateStart() {
		return agreement.getDateStart();
	}

	@JsonInclude
	public String getAgreementUrl() {
		return agreement.getUrl();
	}

	@JsonInclude
	public String getClientTitle() {
		return agreement.getClient().getTitle();
	}

	@JsonInclude
	public Integer getClientId() {
		return agreement.getClient().getId();
	}

	@JsonInclude
	public String getDocTypeShortTitle() {
		return docType.getShortTitle();
	}

	@JsonInclude
	public String getDocStatus() {
		return status.getStatus();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Document [");
		builder.append(super.toString()).append(", ");
		builder.append("docType=" + docType.getShortTitle()).append(", ");
		builder.append("number=" + number).append(", ");
		builder.append("amount=" + amount).append(", ");
		builder.append("dated=" + dated).append(", ");
		builder.append("paymentDate=" + paymentDate).append(", ");
		builder.append("status=" + status.getStatus()).append(", ");
		builder.append("agreement=" + agreement).append("]");
		return builder.toString();
	}

}
