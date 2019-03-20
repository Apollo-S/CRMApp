package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseEntity implements Serializable {

	static final String ID_SEPARATOR = "s/";
	static final String PERIOD_SEPARATOR = " - ";
	static final String DATE_FORMAT = "dd.MM.yyyy";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Version
	@Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
	@JsonIgnore
	private Integer version = 0;

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
		builder.append("id=" + id).append(", ");
		builder.append("version=" + version).append(", ");
		builder.append("url=" + getUrl());
		return builder.toString();
	}

}
