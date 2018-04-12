package crmapp.app.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
public class Client extends AbstractCompany {

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
		this.setTitle(title);
		this.setAlias(alias);
		this.setEdrpou(edrpou);
		this.setInn(inn);
		this.setVatCertificate(vatCertificate);
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
	public int hashCode() {
		return Objects.hash(this.getId(), this.getTitle(), this.getEdrpou());
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
			.append(this.getTitle(), that.getTitle())
			.append(this.getEdrpou(), that.getEdrpou())
			.isEquals();
	}

	@Override
	public String toString() {
		return new StringBuilder("Client [")
			.append(super.toString()).append(", ")
			.append("agreements=" + agreements.size()).append(", ")
			.append("addresses=" + addresses.size()).append(", ")
			.append("directors=" + directors.size()).append(", ")
			.append("accounts=" + accounts.size()).append("]")
			.toString();
	}

}
