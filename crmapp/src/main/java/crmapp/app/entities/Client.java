package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import crmapp.app.entities.base.AbstractCompany;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Tables.CLIENTS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true,
	value = { "hibernateLazyInitializer", "handler",
			"agreements", "addresses", "directors", "accounts" })
public class Client extends AbstractCompany {

    private static final String CLIENT = "client";

	@OneToMany(fetch = FetchType.LAZY, mappedBy = CLIENT, orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = "client-agreement")
	private Set<ClientAgreement> agreements = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = CLIENT, orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = Tables.CLIENT_ADDRESSES)
	private Set<ClientAddress> addresses = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = CLIENT, orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = Tables.CLIENT_DIRECTORS)
	private Set<ClientDirector> directors = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = CLIENT, orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value = Tables.CLIENT_ACCOUNTS)
	private Set<ClientAccount> accounts = new HashSet<>();

	public Client() {
	}

	public Client(String title, String code, String edrpou, String inn, String vatCertificate) {
		this.setTitle(title);
		this.setCode(code);
		this.setEdrpou(edrpou);
		this.setInn(inn);
		this.setVatCertificate(vatCertificate);
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
