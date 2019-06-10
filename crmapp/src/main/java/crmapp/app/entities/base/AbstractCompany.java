package crmapp.app.entities.base;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public abstract class AbstractCompany extends BaseEntity {

	@Column(name = "title", length = 200)
	private String title;

	@Column(name = "code", length = 100)
	private String code;

	@Column(name = "edrpou", length = 12)
	private String edrpou;

	@Column(name = "inn", length = 15)
	private String inn;

	@Column(name = "vat_certificate", length = 20)
	private String vatCertificate;

	public AbstractCompany() {
	}

	public AbstractCompany(String title, String code, String edrpou, String inn, String vatCertificate) {
		this.title = title;
		this.code = code;
		this.edrpou = edrpou;
		this.inn = inn;
		this.vatCertificate = vatCertificate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.getTitle(), this.getEdrpou());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || (getClass() != obj.getClass()))
			return false;
		AbstractCompany that = (AbstractCompany) obj;
		return Objects.equals(this.getId(), that.getId()) && 
				Objects.equals(this.getTitle(), that.getTitle()) && 
				Objects.equals(this.getEdrpou(), that.getEdrpou());
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append(super.toString()).append(", ")
			.append("title=" + title).append(", ")
			.append("code=" + code).append(", ")
			.append("edrpou=" + edrpou).append(", ")
			.append("inn=" + inn).append(", ")
			.append("vatCertificate=" + vatCertificate)
			.toString();
	}

}
