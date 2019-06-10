package crmapp.app.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import crmapp.app.entities.base.AbstractAddress;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Tables.CLIENT_ADDRESSES)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class ClientAddress extends AbstractAddress {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.CLIENT_ID)
	@JsonBackReference(value = Tables.CLIENT_ADDRESSES)
	private Client client;

	public ClientAddress() {
	}

	public ClientAddress(Client client, Date dateStart) {
		this.client = client;
		this.setDateStart(dateStart);
	}

	@JsonInclude
	public Client getClientInfo() {
		return client;
	}

	@Override
	public String getUrl() {
		return client.getUrl() + "/addresses/" + this.getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientAddress [");
		builder.append(super.toString()).append(", ");
		builder.append("client=" + client).append("]");
		return builder.toString();
	}

}
