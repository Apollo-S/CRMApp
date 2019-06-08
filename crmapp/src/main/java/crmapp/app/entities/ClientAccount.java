package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractAccount;
import crmapp.app.entities.base.Contractorable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.CLIENT_ACCOUNTS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class ClientAccount extends AbstractAccount implements Contractorable<Client> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.CLIENT_ID)
	@JsonBackReference(value = Tables.CLIENT_ACCOUNTS)
	private Client client;

	public ClientAccount() {
		client = new Client();
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
	public void setContractor(Client client) {
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
