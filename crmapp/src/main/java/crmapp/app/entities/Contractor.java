package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contractors")
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true,
        value = { "hibernateLazyInitializer", "handler",
                "agreements", "addresses", "directors", "accounts" })
public class Contractor extends BaseEntity {

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "edrpou", length = 12)
    private String edrpou;

    @Column(name = "inn", length = 15)
    private String inn;

    @Column(name = "vat_certificate", length = 20)
    private String vatCertificate;

    @OneToOne
    @JoinColumn(name = "contractor_type_code", referencedColumnName = "code")
    private ContractorType contractorType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractor", orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonManagedReference(value = "contractor-agreement")
    private Set<Agreement> agreements = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractor", orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonManagedReference(value = "contractor-address")
    private Set<Address> addresses = new HashSet<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractor", orphanRemoval = true)
//    @OrderBy("id ASC")
//    @JsonManagedReference(value = "contractor-director")
//    private Set<ClientDirector> directors = new HashSet<>();
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractor", orphanRemoval = true)
//    @OrderBy("id ASC")
//    @JsonManagedReference(value = "contractor-account")
//    private Set<ClientAccount> accounts = new HashSet<>();

    public Contractor() {
    }

    @Override
    public String getUrl() {
        return contractorType.getCode() + "s/" + this.getId();
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Contractor [")
                .append(super.toString()).append(", ")
                .append("title=" + title).append(", ")
                .append("code=" + code).append(", ")
                .append("edrpou=" + edrpou).append(", ")
                .append("inn=" + inn).append(", ")
                .append("vatCertificate=" + vatCertificate).append(", ")
                .append("contractorType=" + contractorType).append("]")
                .toString();
    }
}

