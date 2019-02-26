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
import com.fasterxml.jackson.annotation.JsonInclude;

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
