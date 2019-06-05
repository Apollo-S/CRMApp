package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractAccount;

import javax.persistence.*;
import java.util.Date;

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

	public OurCompanyAccount(String number, String bankName, String mfo, Date dateStart, OurCompany ourCompany) {
		super(number, bankName, mfo, dateStart);
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
