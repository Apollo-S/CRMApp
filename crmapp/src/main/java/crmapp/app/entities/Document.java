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

@Entity
@Table(name = "document")
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
	@JoinColumn(name = "client_agreement_id")
	@JsonBackReference(value = "agreement-document")
	private ClientAgreement agreement;

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public DocumentType getDocType() {
		return docType;
	}

	public void setDocType(DocumentType docType) {
		this.docType = docType;
	}

	public DocumentStatus getStatus() {
		return status;
	}

	public void setStatus(DocumentStatus status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getDated() {
		return dated;
	}

	public void setDated(Date dated) {
		this.dated = dated;
	}

	public ClientAgreement getAgreement() {
		return agreement;
	}

	public void setAgreement(ClientAgreement agreement) {
		this.agreement = agreement;
	}

    public Date getPassingDate() {
        return passingDate;
    }

    public void setPassingDate(Date passingDate) {
        this.passingDate = passingDate;
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
