package crmapp.app.entities.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class AbstractDocumentType extends BaseEntity {

	@Column(name = "title", length = 75)
	private String title;

	public AbstractDocumentType() {
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(super.toString()).append(", ")
			.append("title=" + title)
			.toString();
	}

}
