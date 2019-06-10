package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractMailDocument;

@Entity
@Table(name = "mail_outputs")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class MailOutput extends AbstractMailDocument {

	@Column(name = "receiver", length = 55)
	private String receiver;

	public MailOutput() {
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
			.append("receiver=" + receiver).append("]")
			.toString();
	}

}
