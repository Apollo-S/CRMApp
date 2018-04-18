package crmapp.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "our_company")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler",
		"agreements", "addresses", "directors", "accounts" })
public class OurCompany extends AbstractCompany {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "our_company", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "our-company-address")
	private Set<OurCompanyAddress> addresses = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "our_company", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "our-company-agreement")
	private Set<OurCompanyAgreement> agreements = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "our_company", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "our-company-director")
	private Set<OurCompanyDirector> directors = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "our_company", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "our-company-account")
	private Set<OurCompanyAccount> accounts = new HashSet<>();

	public OurCompany() {
	}

	public OurCompany(String title, String alias, String edrpou, String inn, String vatCertificate) {
		this.setTitle(title);
		this.setAlias(alias);
		this.setEdrpou(edrpou);
		this.setInn(inn);
		this.setVatCertificate(vatCertificate);
	}

	@Override
	public String getUrl() {
		return "our-companies/" + getId();
	}

	@Override
	public String toString() {
		return new StringBuilder("OurCompany [")
			.append(super.toString()).append(", ")
			.append("agreements=" + agreements.size()).append(", ")
			.append("addresses=" + addresses.size()).append(", ")
			.append("directors=" + directors.size()).append(", ")
			.append("accounts=" + accounts.size()).append("]")
			.toString();
	}

}