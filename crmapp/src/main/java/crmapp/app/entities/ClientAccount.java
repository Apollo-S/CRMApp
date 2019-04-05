package crmapp.app.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Tables.CLIENT_ACCOUNTS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class ClientAccount extends AbstractAccount {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.CLIENT_ID)
	@JsonBackReference(value = Tables.CLIENT_ACCOUNTS)
	private Client client;

	public ClientAccount() {
	}

	public ClientAccount(String number, String bankName, String mfo, Date dateStart, Client client) {
		super(number, bankName, mfo, dateStart);
		this.client = client;
	}

	public ClientAccount(Client client, Date dateStart) {
		super();
		this.client = client;
	}

	@Override
	public String getUrl() {
		return client.getUrl() + "/accounts/" + this.getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientAccount [");
		builder.append(super.toString()).append(", ");
		builder.append("client=" + client.getCode()).append("]");
		return builder.toString();
	}

}
