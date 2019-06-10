package crmapp.app.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractDocumentType;

@Entity
@Table(name = "mail_document_type")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class MailDocumentType extends AbstractDocumentType {

	@Override
	public String getUrl() {
		return "mail-document-types/" + this.getId();
	}

}
