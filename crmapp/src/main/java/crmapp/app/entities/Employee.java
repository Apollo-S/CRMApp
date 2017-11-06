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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class Employee extends UrlBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "surname", length = 50)
	private String surname;

	@Column(name = "firstname", length = 50)
	private String firstname;

	@Column(name = "lastname", length = 50)
	private String lastname;

	@Column(name = "short_name", length = 20)
	private String shortName;

	@Column(name = "inn", length = 20)
	private String inn;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

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

	public Employee() {
	}

	public Employee(String surname, String firstname, String lastname, String shortName, String inn, Date birthDate,
			boolean isEntrepreneur, Date hireDate, Date firedDate, Post post) {
		this.surname = surname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.shortName = shortName;
		this.inn = inn;
		this.birthDate = birthDate;
		this.isEntrepreneur = isEntrepreneur;
		this.hireDate = hireDate;
		this.firedDate = firedDate;
		this.post = post;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

}
