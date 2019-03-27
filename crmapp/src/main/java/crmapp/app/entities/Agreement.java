package crmapp.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = Tables.AGREEMENTS)
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"hibernateLazyInitializer", "handler", "documents"})
public class Agreement extends BaseEntity {

    @Column(name = "number")
    private String number;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "agreement_type_code", referencedColumnName = "code")
    private AgreementType agreementType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference(value = "client-agreement")
    private Client client;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value = "employee-agreement")
    private Employee employee;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "our_company_id")
    @JsonBackReference(value = "our-company-agreement")
    private OurCompany ourCompany;

    @OneToMany(fetch = LAZY, mappedBy = "agreement", orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonManagedReference(value = "agreement-document")
    private Set<Document> documents = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Agreement that = (Agreement) obj;
        return Objects.equals(this.getId(), that.getId()) &&
                Objects.equals(this.getNumber(), that.getNumber()) &&
                Objects.equals(this.getDateStart(), that.getDateStart()) &&
                Objects.equals(this.getVersion(), that.getVersion());
    }

    @JsonInclude
    public Object getContractorInfo() {
        if (client != null) {
            return client;
        } else if (employee != null) {
            return employee;
        } else {
            return ourCompany;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getDateStart(), getComment(), getClient(), getDocuments());
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(super.toString()).append(", ")
                .append("number=" + number).append(", ")
                .append("dateStart=" + dateStart)
                .toString();
    }

}
