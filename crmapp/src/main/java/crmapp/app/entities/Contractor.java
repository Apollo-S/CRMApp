package crmapp.app.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "	contractor")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Contractor extends UrlBaseEntity {

	@Column(name = "title")
	private String title;

	@Column(name = "alias")
	private String alias;

	@Column(name = "edrpou")
	private String edrpou;

	@Column(name = "inn")
	private String inn;

	@Column(name = "vat_certificate")
	private String vatCertificate;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contractor", orphanRemoval = true)
	@OrderBy("id ASC")
	@JsonManagedReference(value="contractor-agreement")
	private Set<Agreement> agreements;

	public Contractor() {
	}

	public Contractor(String title, String alias, String edrpou, String inn, String vatCertificate) {
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

}
