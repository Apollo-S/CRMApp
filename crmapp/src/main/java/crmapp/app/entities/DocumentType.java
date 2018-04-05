package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "document_type")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class DocumentType extends AbstractDocumentType {

	@Column(name = "short_title", length = 20)
	private String shortTitle;

	public DocumentType() {
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	@Override
	public String getUrl() {
		return "document-types/" + this.getId();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentType [");
		builder.append(super.toString()).append(", ");
		builder.append("shortTitle=" + shortTitle).append(", ");
		builder.append("url=" + this.getUrl()).append("]");
		return builder.toString();
	}

}
