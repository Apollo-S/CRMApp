package crmapp.app.entities.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseEntity implements Serializable {

	protected static final String ID_SEPARATOR = "s/";
	protected static final String PERIOD_SEPARATOR = " - ";
	protected static final String DATE_FORMAT = "dd.MM.yyyy";
	
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
