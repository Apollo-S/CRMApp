package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "document_type")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DocumentType extends UrlBaseEntity {

	@Column(name = "title")
	private String title;

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

	@Override
	public String toString() {
		return "DocumentType [title=" + title + ", toString()=" + super.toString() + "]";
	}

}
