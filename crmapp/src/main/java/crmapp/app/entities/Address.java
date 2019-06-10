package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crmapp.app.entities.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Tables.ADDRESSES)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "contractors"})
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

    @OneToMany(mappedBy = "address")
    private Set<ContractorAddress> contractors = new HashSet<>();

    public Address() {
    }

    public Address(String zip, String street, String building, String apartment, String city, String region, Country country) {
        this.zip = zip;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
        this.city = city;
        this.region = region;
        this.country = country;
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
    public String toString() {
        return new StringBuilder("Address [")
                .append(super.toString()).append(", ")
                .append("presentation=" + getPresentation()).append(", ")
                .append("]")
                .toString();
    }

}
