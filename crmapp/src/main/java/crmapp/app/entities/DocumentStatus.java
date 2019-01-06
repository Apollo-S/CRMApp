package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "document_status")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class DocumentStatus extends BaseEntity {

	@Column(name = "status")
	private String status;

	@Column(name = "background_color", length = 15)
	private String backgroundColor;

	@Column(name = "color", length = 15)
	private String color;

	public DocumentStatus() {
	}

	public DocumentStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String getUrl() {
		return "document-statuses/" + this.getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentStatus [");
		builder.append(super.toString()).append(", ");
		builder.append("status=" + status).append(", ");
		builder.append("color=" + color).append(", ");
		builder.append("background-color=" + backgroundColor).append(", ");
		builder.append("url=" + this.getUrl()).append("]");
		return builder.toString();
	}

}
