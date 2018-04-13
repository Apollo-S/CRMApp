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
@Table(name = "client_address")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class OurCompanyAddress extends AbstractAddress {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "our_company_id")
	@JsonBackReference(value = "our-company-address")
	private OurCompany company;

	public OurCompanyAddress() {
	}

	@Override
	public String getUrl() {
		return company.getUrl() + "/addresses/" + this.getId();
	}

	@Override
	public String toString() {
		return new StringBuilder()
		.append("OurCompanyAddress [")
		.append(super.toString()).append(", ")
		.append("company=" + company).append("]")
		.toString();
	}

}
