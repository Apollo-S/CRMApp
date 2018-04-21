package crmapp.app.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "our_company_account")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class OurCompanyAccount extends AbstractAccount {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "our_company_id")
	@JsonBackReference(value = "our-company-account")
	private OurCompany ourCompany;

	public OurCompanyAccount() {
	}

	public OurCompanyAccount(OurCompany ourCompany, String presentation, Date dateStart) {
		super(presentation, dateStart);
		this.ourCompany = ourCompany;
	}

	public OurCompany getOurCompany() {
		return ourCompany;
	}

	public void setOurCompany(OurCompany ourCompany) {
		this.ourCompany = ourCompany;
	}

	@Override
	public String getUrl() {
		return ourCompany.getUrl() + "/accounts/" + this.getId();
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("OurCompanyAccount [")
		.append(super.toString()).append(", ")
		.append("ourCompany=" + ourCompany.getTitle()).append("]")
		.toString();
	}

}
