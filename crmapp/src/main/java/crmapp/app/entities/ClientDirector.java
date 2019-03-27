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
@Table(name = Tables.CLIENT_DIRECTORS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class ClientDirector extends AbstractDirector {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.CLIENT_ID)
	@JsonBackReference(value = Tables.CLIENT_DIRECTORS)
	private Client client;

	public ClientDirector() {
	}

	public ClientDirector(Client client, Post post, String fullName, String shortName, Date dateStart) {
		this.client = client;
		this.setPost(post);
		this.setFullName(fullName);
		this.setShortName(shortName);
		this.setDateStart(dateStart);
	}

	@Override
	public String getUrl() {
		return client.getUrl() + "/directors/" + this.getId();
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("ClientDirector [")
			.append(super.toString()).append(", ")
			.append("client=" + client.getTitle()).append("]")
			.toString();
	}

}
