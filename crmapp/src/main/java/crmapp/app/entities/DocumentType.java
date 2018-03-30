package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "document_type")
@NamedQueries({
	@NamedQuery(name = DocumentType.FIND_ALL_DOCUMENT_TYPE_IDS, 
		query = "select dt.id from DocumentType dt")
})
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class DocumentType extends UrlBaseEntity {
	
	static final String FIND_ALL_DOCUMENT_TYPE_IDS = "DocumentType.findAllDocumentTypeIds";

	@Column(name = "title", length = 75)
	private String title;

	@Column(name = "short_title", length = 20)
	private String shortTitle;

	public DocumentType() {
	}

	public DocumentType(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		builder.append("title=" + title).append(", ");
		builder.append("shortTitle=" + shortTitle).append(", ");
		builder.append("url=" + this.getUrl()).append("]");
		return builder.toString();
	}

}
