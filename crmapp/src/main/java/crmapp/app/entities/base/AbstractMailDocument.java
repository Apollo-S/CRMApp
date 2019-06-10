package crmapp.app.entities.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.MailDocumentType;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class AbstractMailDocument extends BaseEntity {

	@Column(name = "number", length = 15)
	private String number;

	@Temporal(TemporalType.DATE)
	@Column(name = "dated")
	private Date dated;

	@Column(name = "comment", length = 255)
	private String comment;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "mail_doc_type_id")
	private MailDocumentType docType;

	public AbstractMailDocument() {
	}

	public AbstractMailDocument(String number, Date dated, String comment, MailDocumentType docType) {
		this.number = number;
		this.dated = dated;
		this.comment = comment;
		this.docType = docType;
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
	public int hashCode() {
		return Objects.hash(this.getId(), this.getNumber(), this.getDated(), this.getDocType());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractMailDocument that = (AbstractMailDocument) obj;
		return Objects.equals(this.getId(), that.getId()) && 
				Objects.equals(this.getNumber(), that.getNumber()) && 
				Objects.equals(this.getDated(), that.getDated()) && 
				Objects.equals(this.getDocType(), that.getDocType());
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(super.toString()).append(", ")
			.append("number=" + number).append(", ")
			.append("dated=" + dated).append(", ")
			.append("docType=" + docType.getTitle()).append(", ")
			.append("comment=" + comment)
			.toString();
	}

}
