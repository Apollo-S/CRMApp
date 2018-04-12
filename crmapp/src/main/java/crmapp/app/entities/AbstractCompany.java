package crmapp.app.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public abstract class AbstractCompany extends BaseEntity {

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

	public AbstractCompany() {
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

	@Override
	public String toString() {
		return new StringBuilder()
			.append(super.toString()).append(", ")
			.append("title=" + title).append(", ")
			.append("alias=" + alias).append(", ")
			.append("edrpou=" + edrpou).append(", ")
			.append("inn=" + inn).append(", ")
			.append("vatCertificate=" + vatCertificate).append("]")
			.toString();
	}

}
