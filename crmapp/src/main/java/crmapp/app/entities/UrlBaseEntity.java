package crmapp.app.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class UrlBaseEntity extends BaseEntity {
	
	private static final String ID_SEPARATOR = "s/";

	public String getUrl() {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(getClass().getSimpleName().toLowerCase());
		urlBuilder.append(ID_SEPARATOR);
		urlBuilder.append(getId());
		return urlBuilder.toString();
	}

	@Override
	public String toString() {
		return "UrlBaseEntity [toString()=" + super.toString() + "]";
	}

}
