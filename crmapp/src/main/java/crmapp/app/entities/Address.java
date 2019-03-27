package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address extends BaseEntity {

    @Column(name = "zip", length = 10)
    private String zip;

    @Column(name = "street", length = 100)
    private String street;

    @Column(name = "building", length = 25)
    private String building;

    @Column(name = "apartment", length = 15)
    private String apartment;

    @Column(name = "city", length = 25)
    private String city;

    @Column(name = "region", length = 30)
    private String region;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contractor_id")
//    @JsonBackReference(value = "contractor-address")
//    private Contractor contractor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "client-address")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value = "employee-address")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "our_company_id")
    @JsonBackReference(value = "our-company-address")
    private OurCompany ourCompany;

    public Address() {
    }

    public Address(String zip, String street, String building, String apartment, String city, String region, Country country, Date dateStart) {
        this.zip = zip;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.city = city;
        this.region = region;
        this.country = country;
        this.dateStart = dateStart;
    }

    public String getPresentation() {
        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(getCountry().getTitle());
        if (getZip() != null && !getZip().isEmpty()) addressBuilder.append(", " + getZip());
        if (getRegion() != null && !getRegion().isEmpty()) addressBuilder.append(", " + getRegion());
        if (getCity() != null && !getCity().isEmpty()) addressBuilder.append(", " + getCity());
        if (getStreet() != null && !getStreet().isEmpty()) addressBuilder.append(", " + getStreet());
        if (getBuilding() != null && !getBuilding().isEmpty()) addressBuilder.append(", " + getBuilding());
        if (getApartment() != null && !getApartment().isEmpty()) addressBuilder.append(", " + getApartment());
        return addressBuilder.toString();
    }

    @Override
    public String getUrl() {
        if (client != null) {
            return client.getUrl() + "/addresses/" + getId();
        } else if (employee != null) {
            return employee.getUrl() + "/addresses/" + getId();
        } else {
            return ourCompany.getUrl() + "/addresses/" + getId();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getPresentation(), this.getDateStart());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || (getClass() != obj.getClass()))
            return false;
        Address that = (Address) obj;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getPresentation(), that.getPresentation()) &&
                Objects.equals(this.getDateStart(), that.getDateStart());
    }

    @Override
    public String toString() {
        return new StringBuilder("Address [")
                .append(super.toString()).append(", ")
                .append("presentation=" + getPresentation()).append(", ")
                .append("dateStart=" + dateStart).append("]")
                .toString();
    }

}
