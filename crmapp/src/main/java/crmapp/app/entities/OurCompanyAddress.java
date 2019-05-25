package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Tables.OUR_COMPANY_ADDRESSES)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"hibernateLazyInitializer", "handler"})
public class OurCompanyAddress extends AbstractAddress {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = Tables.OUR_COMPANY_ID)
    @JsonBackReference(value = Tables.OUR_COMPANY_ADDRESSES)
    private OurCompany ourCompany;

    public OurCompanyAddress() {
    }

    public OurCompanyAddress(OurCompany ourCompany, Date dateStart) {
        this.ourCompany = ourCompany;
        this.setDateStart(dateStart);
    }

    @JsonInclude
    public OurCompany getOurCompanyInfo() {
        return ourCompany;
    }

    @Override
    public String getUrl() {
        return ourCompany.getUrl() + "/addresses/" + this.getId();
    }

    @Override
    public String toString() {
        return new StringBuilder("OurCompanyAddress [")
                .append(super.toString()).append(", ")
                .append("ourCompany=" + ourCompany).append("]")
                .toString();
    }

}
