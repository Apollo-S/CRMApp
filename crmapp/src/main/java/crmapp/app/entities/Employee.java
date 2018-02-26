package crmapp.app.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "employee")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler",
			"vacations", "sickLists", "addresses", "accounts" })
public class Employee extends UrlBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Person person;
	
	@Column(name = "is_entrepreneur", nullable = false)
	private boolean isEntrepreneur = false;

	@Temporal(TemporalType.DATE)
	@Column(name = "hire_date")
	private Date hireDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "fired_date")
	private Date firedDate;

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

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
	@JsonManagedReference(value = "employee-address")
	private Set<EmployeeAddress> addresses = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "employee-account")
	private Set<EmployeeAccount> accounts = new HashSet<>();

	public Employee() {
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isEntrepreneur() {
		return isEntrepreneur;
	}

	public void setEntrepreneur(boolean isEntrepreneur) {
		this.isEntrepreneur = isEntrepreneur;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getFiredDate() {
		return firedDate;
	}

	public void setFiredDate(Date firedDate) {
		this.firedDate = firedDate;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Set<Vacation> getVacations() {
		return vacations;
	}

	public void setVacations(Set<Vacation> vacations) {
		this.vacations = vacations;
	}

	public Set<SickList> getSickLists() {
		return sickLists;
	}

	public void setSickLists(Set<SickList> sickLists) {
		this.sickLists = sickLists;
	}

	public Set<EmployeeAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<EmployeeAddress> addresses) {
		this.addresses = addresses;
	}

	public Set<EmployeeAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<EmployeeAccount> accounts) {
		this.accounts = accounts;
	}
	
	@JsonInclude
	public String getPersonShortName() {
		return person.getShortName();
	}
	
	@JsonInclude
	public String getPersonInn() {
		return person.getInn();
	}
	
	@JsonInclude
	public String getPostTitle() {
		return post.getTitle();
	}

}
