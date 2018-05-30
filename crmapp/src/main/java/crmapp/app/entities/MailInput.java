package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mail_input")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class MailInput extends AbstractMailDocument {

	@Column(name = "sender", length = 55)
	private String sender;

	public MailInput() {
	}

	public MailInput(String sender) {
		super();
		this.sender = sender;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Override
	public String getUrl() {
		return "mail-inputs/" + this.getId();
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("InputMail [")
			.append(super.toString()).append(", ")
			.append("sender=" + sender).append("]")
			.toString();
	}

}
