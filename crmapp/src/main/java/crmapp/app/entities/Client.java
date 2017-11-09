package crmapp.app.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "client")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class Client extends UrlBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "title", length = 255)
	private String title;

	@Column(name = "alias", length = 100)
	private String alias;

	@Column(name = "edrpou", length = 12)
	private String edrpou;

	@Column(name = "inn", length = 15)
	private String inn;

	@Column(name = "vat_certificate", length = 20)
	private String vatCertificate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "client-agreement")
	private Set<Agreement> agreements = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "client-address")
	private Set<ClientAddress> addresses = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "client-director")
	private Set<ClientDirector> directors = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "client-account")
	private Set<ClientAccount> accounts = new HashSet<>();

	public Client() {
	}

	public Client(String title, String alias, String edrpou, String inn, String vatCertificate) {
		this.title = title;
		this.alias = alias;
		this.edrpou = edrpou;
		this.inn = inn;
		this.vatCertificate = vatCertificate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getEdrpou() {
		return edrpou;
	}

	public void setEdrpou(String edrpou) {
		this.edrpou = edrpou;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getVatCertificate() {
		return vatCertificate;
	}

	public void setVatCertificate(String vatCertificate) {
		this.vatCertificate = vatCertificate;
	}

	public Set<Agreement> getAgreements() {
		return agreements;
	}

	public void setAgreements(Set<Agreement> agreements) {
		this.agreements = agreements;
	}

	public Set<ClientAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<ClientAddress> addresses) {
		this.addresses = addresses;
	}

	public Set<ClientDirector> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<ClientDirector> directors) {
		this.directors = directors;
	}

	public Set<ClientAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<ClientAccount> accounts) {
		this.accounts = accounts;
	}

	@Override
	@JsonIgnore
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("title: " + getTitle() + ", ");
		builder.append("alias: " + getAlias() + ", ");
		builder.append("edrpou: " + getEdrpou() + ", ");
		builder.append("inn: " + getInn() + ", ");
		builder.append("vatCertificate: " + getVatCertificate());
		return builder.toString();
	}

}
