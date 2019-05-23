package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = Tables.ADDRESSES)
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

    @ManyToMany(mappedBy = "addresses")
    Set<Contractor> contractors = new HashSet<>();

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
        return "";
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
