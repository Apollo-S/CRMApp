package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "document_status")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DocumentStatus extends UrlBaseEntity {
	
	@Column(name = "status")
	private String status;
	
	

}
