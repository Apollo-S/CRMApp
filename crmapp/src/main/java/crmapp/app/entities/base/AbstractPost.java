package crmapp.app.entities.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
public class AbstractPost extends BaseEntity {

	@Column(name = "title", length = 100)
	private String title;

}
