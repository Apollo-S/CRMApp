package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractDocumentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Tables.DOCUMENT_TYPES)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class DocumentType extends AbstractDocumentType {

	@Column(name = "short_title", length = 20)
	private String shortTitle;

	public DocumentType() {
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
