package crmapp.app.entities;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class UrlBaseEntity extends BaseEntity {
	
	private static final String ID_SEPARATOR = "/";

	@JsonIgnore
	public String getUrl() {
		return getClass().getSimpleName().toLowerCase() + ID_SEPARATOR + getId();
	}

}
