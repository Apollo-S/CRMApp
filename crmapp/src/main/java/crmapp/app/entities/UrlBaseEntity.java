package crmapp.app.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class UrlBaseEntity extends BaseEntity {
	
	static final String ID_SEPARATOR = "s/";
	static final String PERIOD_SEPARATOR = " - ";
	static final String DATE_FORMAT = "dd.MM.yyyy";

	public String getUrl() {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(getClass().getSimpleName().toLowerCase());
		urlBuilder.append(ID_SEPARATOR);
		urlBuilder.append(getId());
		return urlBuilder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append(", ");
		builder.append("url=" + getUrl());
		return builder.toString();
	}

}
