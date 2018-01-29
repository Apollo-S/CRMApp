package crmapp.app.entities;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public class UrlBaseEntity extends BaseEntity {
	
	private static final String ID_SEPARATOR = "/";

	public String getUrl() {
		return getClass().getSimpleName().toLowerCase() + "s" + ID_SEPARATOR + getId();
	}

	@Override
	public String toString() {
		return "UrlBaseEntity [toString()=" + super.toString() + "]";
	}

}
