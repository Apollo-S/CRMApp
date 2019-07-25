package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Tables.POSTS)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "employees"})
public class Post extends BaseEntity {

	@Column(name = "title", length = 100)
	private String title;

	@OneToMany(mappedBy = "post")
	private Set<EmployeePost> employees = new HashSet<>();

}
