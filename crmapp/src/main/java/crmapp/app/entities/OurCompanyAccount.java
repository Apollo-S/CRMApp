package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractAccount;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.OUR_COMPANY_ACCOUNTS)
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class OurCompanyAccount extends AbstractAccount {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = Tables.OUR_COMPANY_ID)
	@JsonBackReference(value = Tables.OUR_COMPANY_ACCOUNTS)
	private OurCompany ourCompany;

	public OurCompanyAccount() {
	}

	public OurCompanyAccount(String number, Bank bank, Date dateStart, OurCompany ourCompany) {
		super(number, bank, dateStart);
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
