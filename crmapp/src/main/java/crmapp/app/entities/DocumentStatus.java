package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "document_status")
@NamedQueries({
	@NamedQuery(name = DocumentStatus.FIND_ALL_DOCUMENT_STATUS_IDS, 
		query = "select ds.id from DocumentStatus ds")
})
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class DocumentStatus extends UrlBaseEntity {

	static final String FIND_ALL_DOCUMENT_STATUS_IDS = "DocumentStatus.findAllDocumentStatusIds";

	@Column(name = "status")
	private String status;

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

}
