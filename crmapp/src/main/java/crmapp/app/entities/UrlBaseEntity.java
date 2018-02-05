package crmapp.app.entities;

import javax.persistence.MappedSuperclass;

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
