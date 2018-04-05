package crmapp.app.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "client")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler",
			"agreements", "addresses", "directors", "accounts" })
public class Client extends BaseEntity {

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
	private Set<ClientAgreement> agreements = new HashSet<>();

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

	public Set<ClientAgreement> getAgreements() {
		return agreements;
	}

	public void setAgreements(Set<ClientAgreement> agreements) {
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
	public int hashCode(){
		return Objects.hash(this.getId(), this.title, this.edrpou);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Client that = (Client) obj;
		return new EqualsBuilder()
			.appendSuper(super.equals(obj))
			.append(this.getId(), that.getId())
			.append(this.title, that.title)
			.append(this.edrpou, that.edrpou)
			.isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [");
		builder.append(super.toString()).append(", ");
		builder.append("title=" + title).append(", ");
		builder.append("alias=" + alias).append(", ");
		builder.append("edrpou=" + edrpou).append(", ");
		builder.append("inn=" + inn).append(", ");
		builder.append("vatCertificate=" + vatCertificate).append("]");
		return builder.toString();
	}

}
