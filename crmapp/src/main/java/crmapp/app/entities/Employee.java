package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import crmapp.app.entities.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Tables.EMPLOYEES)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler",
			"vacations", "sickLists", "addresses", "accounts", "agreements", "posts"})
public class Employee extends BaseEntity {

	public static final String ENTITY_NAME = "employee";

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = Tables.PERSON_ID)
	private Person person;
	
	@Column(name = "is_entrepreneur", nullable = false)
	private boolean isEntrepreneur = false;

	@Temporal(TemporalType.DATE)
	@Column(name = "hire_date")
	private Date hireDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "fired_date")
	private Date firedDate;

	@OneToMany(mappedBy = "employee")
	private Set<EmployeePost> posts = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "employee-vacation")
	private Set<Vacation> vacations = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "employee-sicklist")
	private Set<SickList> sickLists = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = Tables.EMPLOYEE_ADDRESSES)
	private Set<EmployeeAddress> addresses = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = Tables.EMPLOYEE_ACCOUNTS)
	private Set<EmployeeAccount> accounts = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "employee-agreement")
	private Set<Agreement> agreements = new HashSet<>();

	public Employee() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [");
		builder.append(super.toString()).append(", ");
		builder.append("person=" + person).append(", ");
		builder.append("isEntrepreneur=" + isEntrepreneur).append(", ");
		builder.append("hireDate=" + hireDate).append(", ");
		builder.append("firedDate=" + firedDate).append(", ");
		return builder.toString();
	}

}
