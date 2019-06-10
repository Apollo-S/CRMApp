package crmapp.app.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.AbstractDirector;

@Entity
@Table(name = "our_company_director")
@JsonIgnoreProperties(ignoreUnknown = true, 
	value = { "hibernateLazyInitializer", "handler" })
public class OurCompanyDirector extends AbstractDirector {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "our_company_id")
	@JsonBackReference(value = "our-company-director")
	private OurCompany ourCompany;

	public OurCompanyDirector() {
	}

	public OurCompanyDirector(OurCompany company, Post post, String fullName, String shortName, Date dateStart) {
		this.ourCompany = company;
		this.setPost(post);
		this.setFullName(fullName);
		this.setShortName(shortName);
		this.setDateStart(dateStart);
	}

	public OurCompany getOurCompany() {
		return ourCompany;
	}

	public void setOurCompany(OurCompany ourCompany) {
		this.ourCompany = ourCompany;
	}
	
	@Override
	public String getUrl() {
		return ourCompany.getUrl() + "/directors/" + this.getId();
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("OurCompanyDirector [")
			.append(super.toString()).append(", ")
			.append("ourCompany=" + ourCompany.getTitle()).append("]")
			.toString();
	}

}
