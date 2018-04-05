package crmapp.app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "person")
@JsonIgnoreProperties(
		ignoreUnknown = true, 
		value = { "hibernateLazyInitializer", "handler" })
public class Person extends BaseEntity {

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

	public Person() {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [");
		builder.append(super.toString()).append(", ");
		builder.append("surname=" + surname).append(", ");
		builder.append("firstname=" + firstname).append(", ");
		builder.append("lastname=" + lastname).append(", ");
		builder.append("shortName=" + shortName).append(", ");
		builder.append("inn=" + inn).append(", ");
		builder.append("birthDate=" + birthDate).append("]");
		return builder.toString();
	}

}