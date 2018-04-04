package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class AbstractDocumentType extends UrlBaseEntity {

	@Column(name = "title", length = 75)
	private String title;

	public AbstractDocumentType() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append(super.toString()).append(", ")
			.append("title=" + title)
			.toString();
	}

}
