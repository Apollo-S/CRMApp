package crmapp.app.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "client_director")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class ClientDirector extends AbstractDirector {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	@JsonBackReference(value = "client-director")
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String getUrl() {
		return client.getUrl() + "/directors/" + this.getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClientDirector [");
		builder.append(super.toString()).append(", ");
		builder.append("client=" + client.getTitle()).append(", ");
//		builder.append("post=" + post.getTitle()).append(", ");
//		builder.append("fullName=" + fullName).append(", ");
//		builder.append("shortName=" + shortName).append(", ");
//		builder.append("dateStart=" + dateStart).append("]");
		return builder.toString();
	}

}
