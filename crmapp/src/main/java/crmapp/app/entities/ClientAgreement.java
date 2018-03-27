package crmapp.app.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "client_agreement")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler", "documents" })
public class ClientAgreement extends AbstractAgreement {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@JsonBackReference(value = "client-agreement")
	private Client client;

	public ClientAgreement() {
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@JsonInclude
	public Integer getClientId() {
		return client.getId();
	}

	@JsonInclude
	public String getClientAlias() {
		return client.getAlias();
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
		builder.append("client=" + client).append("]");
		return builder.toString();
	}

}
