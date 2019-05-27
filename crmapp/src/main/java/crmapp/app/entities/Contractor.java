package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Tables.CONTRACTORS)
@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"hibernateLazyInitializer", "handler",
                "agreements", "addresses", "directors", "accounts"})
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
    @JoinColumn(name = "contractor_type_id", referencedColumnName = "id")
    private ContractorType contractorType;

    @OneToMany(mappedBy = "contractor")
    private Set<ContractorAddress> addresses = new HashSet<>();

    public Contractor() {
    }

    @Override
    public String getUrl() {
        return contractorType.getCode() + "/" + this.getId();
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

