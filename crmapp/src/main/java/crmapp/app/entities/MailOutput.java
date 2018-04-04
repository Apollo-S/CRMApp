package crmapp.app.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mail_output")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class MailOutput extends UrlBaseEntity {

	@Column(name = "number", length = 15)
	private String number;
	
	@Column(name = "receiver", length = 55)
	private String receiver;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dated")
	private Date dated;
	
	@Column(name = "comment", length = 255)
	private String comment;
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "mail_doc_type_id")
	private MailDocumentType docType;

	public MailOutput() {
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Date getDated() {
		return dated;
	}

	public void setDated(Date dated) {
		this.dated = dated;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public MailDocumentType getDocType() {
		return docType;
	}

	public void setDocType(MailDocumentType docType) {
		this.docType = docType;
	}

	@Override
	public String getUrl() {
		return "mail-outputs/" + this.getId();
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("OutputMail [")
			.append(super.toString()).append(", ")
			.append("docType=" + docType.getTitle()).append(", ")
			.append("number=" + number).append(", ")
			.append("receiver=" + receiver).append(", ")
			.append("dated=" + dated).append(", ")
			.append("comment=" + comment).append("]")
			.toString();
	}

}
